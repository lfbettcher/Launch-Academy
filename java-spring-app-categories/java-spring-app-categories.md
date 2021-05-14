*Here, we're building on the code built out in Java Spring Data and Java Spring Data Seeders. We've provided final code from those articles for you.*

## Core Tasks

- Create an `AppCategory` entity that has a unique `name`. Be sure to include the correlating migration, entity, and repository.
- Create a new seeder routine that seeds the categories below. Be sure to ensure this seeder class can be run multiple times without causing duplication in the table.
  - Social Media
  - Productivity
  - Games
  - Self Help
- Update your Idea entity with the following repository methods:

  ***You may need to look at the docs for repositories, or to create custom queries to complete some of the following***
  - Return all `Ideas`
  - Return a specific `Idea` by id
  - Return a specific `Idea` by name
  - Return an `Idea` which doesn't contain the word "Facebook"
  - Return all `Idea`s that start with "Cat"
    - For an extra challenge figure out how to make this work with "Cat" or "cat" using a JPA query-method
  - Return all `Idea`s that end with "book"

*Do not worry about associating your ideas with categories for now*
