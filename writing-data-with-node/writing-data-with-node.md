To date, we've been displaying data from our database in the context of our Express Web applications. Our web applications become truly functional when users can provide input that will populate a database.

In this article, we'll connect form data with the database.

## Learning Goals

- Define a schema constraint
- Save valid user data to a database
- Handle for invalid data supplied by a user

## Getting Started

```no-highlight
et get writing-data-with-node
cd writing-data-with-node
nvm use 12 (on mac) || nvm use 12.18.3 (on pc)
yarn install
createdb launch_journal
psql launch_journal

launch_journal=# \i db/schema.sql
```

## Understanding Our Schema

We're going to create a journaling application for your Launch Academy experience. I know it's late in the game, but starting now is better than never!

### Data Definition

Let's take a peek at the `schema.sql` file we just executed:

```sql
DROP TABLE IF EXISTS journal_entries;
CREATE TABLE journal_entries (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  body TEXT NOT NULL,
  entered_at TIMESTAMP NOT NULL
)
```

### Schema Constraints

The first SQL statement is destructive. If the `journal_entries` table already exists, this `DROP TABLE` statement will remove the table.

Notice that in our `CREATE TABLE` statement, we're creating three **required** fields. In addition to defining the data types of our fields, we can also specify whether they are required fields or not. Since a `journal_entry` basically can't exist without a title, a body, and a time at which it was entered, we protect ourselves against [Garbage-In-Garbage-Out (GIGO)][gigo]. These `NOT NULL` statements disallow `INSERT` operations from completing unless it meets our requirements.

With the schema in place, let's execute some errant SQL via `psql`.

```sql
INSERT INTO journal_entries (title) VALUES ('Epic Rant');
```

When we execute this SQL, psql will output an error.

```no-highlight
ERROR:  null value in column "body" violates not-null constraint
DETAIL:  Failing row contains (null, Epic Rant, null, null).
```

We call these restrictions **schema constraints**. With the `NOT NULL` keywords, we require that all fields in `journal_entries` be populated before we write a record to the table.

## Building Our Input Form

When we last looked at forms in an purely backend context, we created a `/new` path the allow users to add entries to our system. We'll do the same here, and present the user with a form to enter in a journal entry. We'll then write an express endpoint that responds to the form's `POST` request.

### Building Out Our Form

First, we'll need to create our `GET` endpoint so that the user can add a journal entry. Let's modify `server/server.js` to support this operation.

```javascript
app.get("/journal-entries/new", (req, res) => {
  res.render("journal-entries/new", { journalEntry: {} })
})
```

We'll then need to create our view template `views/journal-entries/new.hbs`:

```html
<h1>Add a New Journal Entry</h1>

<form action="/journal-entries" method="POST">
  <div>
    <label>Title</label>
    <input type="text" name="journalEntry[title]" />
  </div>

  <div>
    <label>Body</label>
    <textarea name="journalEntry[body]"></textarea>
  </div>

  <div>
    <input type="submit" value="Add Entry" />
  </div>
</form>
```

Notice that we've changed the way we name our input elements. We'll study that when we define our `POST` endpoint in `server/server.js`.

```javascript
app.post("/journal-entries", (req, res) => {
  console.log(req.body)
  res.redirect("/journal-entries/new")
})
```

You'll now need to start your server in a separate terminal window.

With the above endpoint in place, navigate to `/journal-entries/new` and supply "Entry Title" for the title and "Entry Description" for the body. Submit the form.

When we submit the form, we can see the following output in the logs:

```no-highlight
{ journalEntry: { title: 'Entry Title', body: 'Entry Description' } }
```

Using extended URL encoding, we can nest form elements contextually. Here, we've created two form inputs for the `journalEntry` object. That way, in our `POST` server endpoint, we can refer to this object as `req.body.journalEntry`.

The middleware that enables us to do this is defined above as boilerplate we've previously supplied to you.

```javascript
app.use(bodyParser.urlencoded({ extended: true }))
```

Without the `extended` option, we wouldn't be able to nest our field values in such a way. Thanks again to open source software for a wonderful and convenient feature.

### Building Our INSERT Statement

With our form properly in place, we can modify our `POST` handler to add our journal entry into the database. First, we'll want to define our connection pool in `server/server.js`.

```javascript
import pg from 'pg'

const pool = new pg.Pool({
  connectionString: "postgres://postgres:password@127.0.0.1:5432/launch_journal"
})
```

Taking what we've learned from our seeding exercise, we can modify our endpoint to process our INSERT statement.

```javascript
app.post("/journal-entries", (req, res) => {
  const journalEntry = req.body.journalEntry
  pool
    .query("INSERT INTO journal_entries (title, body) VALUES ($1, $2)", [
      journalEntry.title,
      journalEntry.body
    ])
    .then(result => {
      res.redirect("/journal-entries/new")
    })
    .catch(err => {
      res.sendStatus(500)
    })
})
```

When we submit our form, notice that we, perhaps unexpectedly, get a 500 error! We forgot about the `entered_at` timestamp. Let's modify our query to account for that. `new Date()` will create a date object with values matching today's date, which is exactly what we need.

```javascript
app.post("/journal-entries", (req, res) => {
  const journalEntry = req.body.journalEntry
  const enteredAt = new Date()
  pool
    .query("INSERT INTO journal_entries (title, body, entered_at) VALUES ($1, $2, $3)", [
      journalEntry.title,
      journalEntry.body,
      enteredAt
    ])
    .then(result => {
      res.redirect("/journal-entries/new")
    })
    .catch(err => {
      console.log(err)
      res.sendStatus(500)
    })
})
```

Now, if we specify both a title and a body when we complete the form, the record successfully writes to the database, and we are redirected.

Remember, though, that users don't always do what we expect. So, what happens when they inevitably forget to enter an entry title or body?

### Validating User Input

Right now, thanks to our schema constraints, the server will respond with a 500 status code. This is one approach we can take, but it is not very user friendly. Instead, we should validate the input and allow the user to correct their error.

To do this we can add some simple logic to the top of our `POST` endpoint.

```javascript
const journalEntry = req.body.journalEntry
//get the title and body properties of journalEntry
const { title, body } = journalEntry

//initialize a list of errors
const errors = []
//if title is falsy or blank
if (!title || title === "") {
  //add an error
  errors.push("Title can't be blank")
}
//if body is falsy or blank
if (!body || body === "") {
  //add an error
  errors.push("Body can't be blank")
}
```

This routine essentially builds a list of applicable errors based on what the user entered. We'll use these errors to decide whether the record should be written to the database or not.

```javascript
if (errors.length === 0) {
  //no errors are encountered - we can save to the database
  const enteredAt = new Date()
  pool
    .query(
      "INSERT INTO journal_entries (title, body, entered_at) VALUES ($1, $2, $3)",
      [journalEntry.title, journalEntry.body, enteredAt]
    )
    .then(result => {
      res.redirect("/journal-entries/new")
    })
    .catch(err => {
      console.log(err)
      res.sendStatus(500)
    })
} else {
  //re-render the form and give the user a chance to fix things
  res.render("journal-entries/new", { journalEntry: journalEntry, errors: errors })
}
```

If there are no errors in the array, we can attempt to issue our SQL query. Otherwise, we want to render our form again so that the user can address issues with their input.

Let's modify our view template to make use of this validation information.

First, we add a display for any applicable errors if the user enters an item incorrectly.

```handlebars
<h1>Add a New Journal Entry</h1>

{{#if errors }}
<ul>
  {{#each errors}}
  <li>{{ this }}</li>
  {{/each}}
</ul>
{{/if }}
```

This will render an unordered list if errors are present. It will output an error inside an `<li>` tag.

Secondly, we use the previously entered values to prefill the form. That way, the user can see that we've retained what they previously entered.

```handlebars
<form action="/journal-entries" method="POST">
  <div>
    <label>Title</label>
    <input type="text" name="journalEntry[title]" value="{{ journalEntry.title }}" />
  </div>

  <div>
    <label>Body</label>
    <textarea name="journalEntry[body]">{{ journalEntry.body }}</textarea>
  </div>

  <div>
    <input type="submit" value="Add Entry" />
  </div>
</form>
```

Note that we have access to the `journalEntry` object here. In the `else` statement of our POST endpoint in our server, we rendered with the following code:

```javascript
res.render("journal-entries/new", { journalEntry: journalEntry, errors: errors })
```

As you can see, we not only pass back our validation errors, but also the journal entry object we created to read the parameters from the user's submitted form data. We can pass this data back to our view template once we re-render it in order to render the form with the values prefilled! In this way, if the user did fill out the `body` of the form but forgot the `title`, they won't lose the data they have already provided when the form re-renders.

## Why This Matters

Processing user-generated content in our Web Applications is what makes them truly useful. Whether we're entering shipping information for an eCommerce store, or if we're issuing a search against a search engine.

In the context of our Web Applications, saving what users enter is typically facilitated by a database. Once we've mastered the ability of working with a database with our backend code, we can write applications that can scale to thousands of users.

## In Summary

We typically process input generated by web forms and save the correlating data to a database. We can safely execute `INSERT` statements with the `pg` package and prepared statements.

It's important to ensure users provide us with high quality data. This protects us against Garbage-In Garbage-Out (GIGO). We can validate form-supplied data on the backend via our Express applications.

[gigo]:https://en.wikipedia.org/wiki/Garbage_in,_garbage_out
