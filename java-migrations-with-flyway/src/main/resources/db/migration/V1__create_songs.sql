CREATE TABLE songs (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  album VARCHAR(255) NOT NULL,
  artist VARCHAR(255) NOT NULL
);