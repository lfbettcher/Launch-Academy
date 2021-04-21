In this exercise, you'll get comfortable with JPQL by answering a few questions about our `movies` database.

 We created this database in week 8's `database-drills` article. If you no longer have that table here are the steps to recreate it.

```sql
createdb movie_database
curl -o movie_database.sql.gz https://s3.amazonaws.com/launchacademy-downloads/movie_database.sql.gz
gunzip ./movie_database.sql.gz
psql movie_database
\i movie_database.sql
```


## Movies and Queries

Create a `Trivia` class with a static `main` method that answers the following questions programmatically:

- What titles were released in 2001?
- What titles were released in 2002 and had a rating higher than 90?
- What actors have the last name of Wilson?
- What actors have the first name of Owen?
- What studios start with "Fox"?
- What studios involve Disney?
- What were the top 5 rated movies in 2004?
- What were the worst 10 movie titles and their ratings in 2003?
