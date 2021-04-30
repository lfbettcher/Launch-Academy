DROP TABLE IF EXISTS dogs;

CREATE TABLE breeds (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE UNIQUE INDEX breeds_name_index ON breeds (name);

CREATE TABLE dogs (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  photo_url VARCHAR(255) NOT NULL,
  breed_id INTEGER NOT NULL REFERENCES breeds (id),
  sex VARCHAR(255) NOT NULL
);