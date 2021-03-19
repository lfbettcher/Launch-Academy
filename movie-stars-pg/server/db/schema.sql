CREATE TABLE movies (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  genre VARCHAR(255) NOT NULL,
  description TEXT
);
INSERT INTO movies(title, genre, description)
VALUES ('Movie 1', 'Genre 1', 'Description 1'),
('Movie 2', 'Genre 2', 'Description 2'),
('Movie 3', 'Genre 3', 'Description 3');