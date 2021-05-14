DROP TABLE IF EXISTS parties, friends;

CREATE TABLE parties (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  location VARCHAR(255) NOT NULL
);

INSERT INTO parties (name, description, location)
VALUES ('Party Name 1', 'Description 1', 'location 1'),
       ('Party Name 2', 'Description 2', 'location 2'),
       ('Party Name 3', 'Description 3', 'location 3');

CREATE TABLE friends (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL
);

INSERT INTO friends (first_name, last_name)
VALUES ('first name 1', 'last name 1'),
       ('first name 2', 'last name 2'),
       ('first name 3', 'last name 3');
