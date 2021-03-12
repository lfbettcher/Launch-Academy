```no-highlight
As a user
I want to see a list of drinks
So I can get a sense of what is keeping people sane
```

Acceptance Criteria:

- When a user navigates to the root path (`/`), they should see the drink's title (but not the content) of all the drinks in the application.

```no-highlight
As a user
I want to see a specific drink
So I can get details about that drink
```

Acceptance Criteria:

- Clicking on a drink listed on the index page should direct the user to that drink's show page, at `/drinks/((drink id here))`. For example, if I click on the title of an drink with an id of `4`, I should be taken to `/drinks/4`.
- Similarly, if I navigate directly to `/drinks/4` in my browser (by typing in the URL), I should be taken to the show page for the drink with an id of `4`
- On the `/drinks/((drink id here))` page, the user should see the drink's title and the body (and not the title or body of any other drink!).

### Step 3

```no-highlight
As a user
I want to type into a form
So I can contribute to an awesome list of drinks
```

Acceptance Criteria:

- A user should be able to type into the form, have the form update accordingly (displaying whatever they have typed), and have their typed information tracked in the form components state.
