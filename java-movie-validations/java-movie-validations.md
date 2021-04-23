## Getting Started

```no-highlight
et get java-movie_validations
cd java-movie_validations
idea .
```

***Note: You may need to run dropdb movies prior to the following***

```sql
createdb movies
curl -o movie_database.sql.gz https://s3.amazonaws.com/launchacademy-downloads/movie_database.sql.gz
gunzip ./movie_database.sql.gz
psql movies
\i movie_database.sql
```

## Validating Movies

- Validate that a movie title must be between 1 and 255 characters
- Validate that a year is greater than or equal to 1920
- Validate that a rating is between 0 and 100 (allow nulls)

## Map and Validate the Studio Class

- Build out an entity that correlates with the `studios` table
- Validate that the name is present and unique

## Map and Validate the Genre Class

- Build out an entity that correlates with the `genres` table
- Validate that the name is present and unique

### Noncore - Relationships

***Note: You will need to read java-hibernate-joins-and-associations (article) to complete these stories***

- associate movies with their studios
- associate movies with their genre
- write migrations to update your database accordingly
