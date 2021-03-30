Thus far, we've been working with pre-established database schema. One thing that is consistent in software development projects is change. Our database schema must have the flexibility to adapt with the software's needs as requirements change. That's where a tool like Flyway comes in. Flyway provides us with the ability to iteratively develop our database schema. This article will explore how to configure and use this tool.

## Learning Goals

- Install and configure Flyway
- Initialize a Flyway-managed schema
- Introduce database changes with a new Flyway migration

## Getting Started

We're going to work with a new domain. Let's create a `songs` database.

```no-highlight
createdb songs
et get java-migrations-with-flyway
cd java-migrations-with-flyway
idea .
```

We've provided a starting point for your application.

## The Need for Database Migrations

It's very likely that our database schema will change over time. As applications mature and requirements change, we have to modify our schema to handle new information or find better ways to represent what we already have. Applications tend to accumulate many incremental changes that move the database from one state to the next.

It's important to maintain an ordering of these changes so that we can re-build the schema from scratch and determine what new changes are required. For example, if we add a column to a table on our development database, we need to record that change somewhere so we can also apply it to our production database. If our team has multiple developers, it is important that they apply the same changes to _their_ development databases so that everyone's databases stay in sync.

Database migrations are files that tell that story over time. With database migrations, we can create a database from scratch. Regardless of the machine, operating system, or current state of the codebase, we can always get up to date with the latest version of the schema.

Flyway is the library we'll use for migrating our Hibernate applications. Note, that another popular tool exists, [liquibase][liquibase], that has similar functionality. We chose Flyway because it is more commonly used, and it is a bit more straightforward to use if you already know SQL Data Definition Language (DDL).

## Installing Flyway

Flyway works best with our workflow as a maven plugin. We'll again have to duplicate some database connection information for now. Let's add the plugin inside of our `<project>` xml in `pom.xml`.

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-maven-plugin</artifactId>
      <version>5.2.4</version>
      <configuration>
        <url>jdbc:postgresql://localhost:5432/songs</url>
        <user>postgres</user>
        <password>password</password>
      </configuration>
    </plugin>
  </plugins>
</build>
```

If we've enabled auto import, we can click refresh on the "Maven Project" section of Intellij and we'll see we have a series of new tasks we can run.
First, we need to create a new folder in our `resources` directory. Create a `db/migration` folder structure inside your project, so that the folder `<project-root>/src/main/resources/db/migration` exists.



## Creating our Songs table

Let's start fresh with our songs database.

```no-highlight
dropdb songs
createdb songs
```


We're going to create our first migration. Inside our `db/migration` folder, we're going to create a `V1__create_songs.sql` file. Place the following contents in that file.

```sql
CREATE TABLE songs (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  album VARCHAR(255) NOT NULL,
  artist VARCHAR(255) NOT NULL
);
```
**Note: Running Flyway to create the table from our Migration removes the need for us to explicitly create the id_sequence on the table, since it does it automatically for us!**


Once that has been created, we're going to initialize our Flyway managed schema. We'll use the provided tasks in our Maven drawer to do so.

To open up your Maven drawer, look on the right-hand side of your IntelliJ editor. You should see two icons: One for "Ant" and one for "Maven". Click "Maven", then expand the "Plugins" directory shown in the drawer. Here you will find the Flyway commands you are able to run through IntelliJ.

Save this file and run `flyway:migrate` from the "Maven Project" drawer of Intellij.

When we run this task, it will execute the SQL found in our newly created file. This is how we'll affect schema changes moving forward. With each schema change, we'll bump the number next to the `V` in the filename.

You can verify the existence of the `songs` table with `psql` on the command line.

```no-highlight
psql songs
=# \d
```

Flyway uses **convention over configuration**. As long as we prefix our file names in `db/migration` with a `V<num>__` it will run that SQL sequentially to ensure we're always running the latest database schema.

Let's review the anatomy of a migration file name, using `V1__create_songs` file name as our example. The file name consists of the following parts:

- Prefix: V for versioned migrations, U for undo migrations, R for repeatable migrations
  - We use V for creation and updating of tables.
- Version: Tells the reader where this migration belongs in the migration order. Use Underscores to separate as many parts as you like. (automatically replaced by dots at runtime) (Not for repeatable migrations).  This should always increase by 1 over the previous migration.
- Separator: __ (two underscores) separates the version from the description.
- Description: Tells the reader what the migration is designed to do. Use underscores to separate the words (automatically replaced by spaces at runtime). Be sure to be explicit and follow the convention set by the company or project you are working on.

## Modifying our table

We forgot to add the `genre` field to our table. Let's do so now via a second migration. We can add `V2__add_genre_to_songs.sql` to our `db/migration` folder.

```sql
ALTER TABLE songs
ADD COLUMN genre VARCHAR(255);
```

We can again run the `flyway:migrate` task and the `songs` table will be modified to reflect this new field.

Flyway will keep track of what migrations have run in the `flyway_schema_history` table. You can observe this using the `psql` command.

```no-highlight
psql songs
=# SELECT * FROM flyway_schema_history;
```

## Reverting Changes

One of the drawbacks of Flyway is that it requires a purchased license to undo (or **rollback**) a migration. One thing you can do in development is drop your database, recreate it, and run `flyway:migrate` again if you make a mistake. This is _only_ an option when you're still actively working on developing the given portion of your app.

For changes that make it to production, you will have to create another migration to effectively undo the migration you built. For example, if we wanted to remove the `genre` field we just added, we would add `V3___drop_genre_on_songs.sql`.

```sql
ALTER TABLE songs
DROP COLUMN genre;
```

## Why This Matters

We can manage our schema over time with code. Thanks to Flyway, all developers working collaboratively on a software project can all be running the same version of the schema, without a ton of coordination. This is also impactful for when our product goes to production, as we can enact incremental schema changes on the database with relative ease.

## Resources

- Flyway's Website: https://flywaydb.org/

## In Summary

Use the Flyway library and migrations to manage changes to your database schema. Be sure to observe Flyway's file naming conventions, and it will handle the rest when you run `flyway:migrate`.

[liquibase]:https://www.liquibase.org/
