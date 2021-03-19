-- Retrieval
-- What titles were released in 2003?
SELECT m.title FROM movies m
WHERE m.year = 2003;

-- What titles were released in 2004 and had a rating higher than 90?
SELECT m.title FROM movies m
WHERE m.year = 2004 AND m.rating > 90;

-- What actors have the last name of Wilson?
SELECT a.name FROM actors a
WHERE a.name LIKE '% Wilson';

-- What actors have the first name of Owen?
SELECT a.name FROM actors a
WHERE a.name LIKE 'Owen %';

-- What studios start with "Fox"?
SELECT s.name FROM studios s
WHERE s.name LIKE 'Fox %';

-- What studios involve Disney?
SELECT s.name FROM studios s
WHERE s.name LIKE '%Disney%';

-- What were the top 5 rated movies in 2005?
SELECT m.title FROM movies m
WHERE m.year = 2005
ORDER BY m.rating DESC NULLS LAST
LIMIT 5;

-- What were the worst 10 movie titles and their ratings in 2000?
SELECT m.title, m.rating FROM movies m
WHERE m.year = 2000
ORDER BY m.rating ASC NULLS LAST
LIMIT 10;

-- Advanced Retrieval
-- What movie titles were produced by Walt Disney Pictures in 2010?
SELECT m.title
FROM movies m
  JOIN studios s ON m.studio_id = s.id
WHERE m.year = 2010 AND s.name = 'Walt Disney Pictures';

-- Who were the characters in "The Hunger Games"?
SELECT c.character
FROM cast_members c
  JOIN movies m ON c.movie_id = m.id
WHERE m.title = 'The Hunger Games';

-- Who acted in "The Hunger Games"?
SELECT a.name
FROM actors a
  JOIN cast_members c ON a.id = c.actor_id
  JOIN movies m ON c.movie_id = m.id
WHERE m.title = 'The Hunger Games';

-- Who acted in a Star Wars movie? Be sure to include all movies.
SELECT a.name
FROM actors a
  JOIN cast_members c ON a.id = c.actor_id
  JOIN movies m ON c.movie_id = m.id
WHERE m.title LIKE '%Star Wars:%';

-- What were all of the character names for movies released in 2009?
SELECT c.character
FROM cast_members c
  JOIN movies m ON c.movie_id = m.id
WHERE m.year = 2009 AND c.character IS NOT NULL;

-- What are the character names in the "The Dark Knight Rises"?
SELECT c.character, c.movie_id, m.id, m.title
FROM cast_members c
  JOIN movies m ON c.movie_id = m.id
WHERE m.title = 'The Dark Knight Rises' AND c.character IS NOT NULL;

-- What actors and actresses have been hired by Buena Vista?
SELECT a.name
FROM actors a
  JOIN cast_members c ON a.id = c.actor_id
  JOIN movies m ON c.movie_id = m.id
  JOIN studios s ON m.studio_id = s.id
WHERE s.name = 'Buena Vista';

-- Updating
-- Troll 2 was the best movie ever. Let's update it to have a rating of 500.
UPDATE movies
SET rating = 500, updated_at = NOW()
WHERE title = 'Troll 2';

-- "Police Academy 4 - Citizens on Patrol" was underrated. Let's give it a 20.
UPDATE movies
SET rating = 20, updated_at = NOW()
WHERE title = 'Police Academy 4 - Citizens on Patrol';

-- Matt Damon has updated his name to "The Artist Formerly Known as Matt Damon".
-- Let's update the database to reflect this momentous change in the film industry.
UPDATE actors
SET name = 'The Artist Formerly Known as Matt Damon', updated_at = NOW()
WHERE name = 'Matt Damon';

-- Deletion
-- We want to forget Back to the Future Part III ever happened.
-- Delete only that movie. Be sure to delete correlating cast_member entries first.
DELETE FROM cast_members USING movies
WHERE movie_id = movies.id
  AND movies.title = 'Back to the Future Part III';
DELETE FROM movies
WHERE title = 'Back to the Future Part III';

-- Horror movies are too scary - delete every Horror movie.
-- Don't forget about their correlating cast_members entries.
-- deletes 2544 rows from cast_members, 115 rows from movies
DELETE FROM cast_members c
WHERE movie_id IN (
  SELECT m.id FROM movies m
    JOIN genres g ON m.genre_id = g.id
  WHERE g.name = 'Horror'
  );
DELETE FROM movies m USING genres g
WHERE m.genre_id = g.id AND g.name = 'Horror';

-- Ben Affleck movies are also too scary - delete every movie he acted in. Wasn't that therapeutic?
-- deletes 26 rows from movies
DELETE FROM movies m
WHERE id IN (
  SELECT c.movie_id FROM cast_members c
    JOIN actors a ON c.actor_id = a.id
  WHERE a.name = 'Ben Affleck'
  );

-- Fake news - we're revising history for 20th Century Fox. Delete any movie they produced that has a rating of less than 80.
-- deletes 144 rows from movies (145 if previous queries not executed)
DELETE FROM movies m
WHERE studio_id IN (
    SELECT s.id FROM studios s
    WHERE s.name = '20th Century Fox'
  )
  AND m.rating < 80;

-- reviews table: description, score from 0-100, author name, and it should relate to a movie
CREATE TABLE reviews (
  id SERIAL PRIMARY KEY,
  description VARCHAR(255),
  score SMALLINT NOT NULL CHECK(
    score >= 0
    AND score <= 100
  ),
  author_name VARCHAR(255),
  movie_id INTEGER REFERENCES movies(id),
  created_at timestamp DEFAULT NOW(),
  updated_at timestamp DEFAULT NOW()
);

-- crimes table: year of offense, the title of the offense, and it should relate to the actor.
CREATE TABLE crimes (
  id SERIAL PRIMARY KEY,
  year INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  actor_id INTEGER REFERENCES actors(id),
  created_at timestamp DEFAULT NOW(),
  updated_at timestamp DEFAULT NOW()
);
