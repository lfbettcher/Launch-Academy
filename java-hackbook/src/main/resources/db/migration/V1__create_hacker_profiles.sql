CREATE TABLE hacker_profiles (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email_address VARCHAR(255) NOT NULL,
  favorite_language VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL
);