DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  price NUMERIC(15, 6) NOT NULL,
  url VARCHAR(255)
);