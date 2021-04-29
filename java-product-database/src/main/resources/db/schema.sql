DROP TABLE IF EXISTS products, categories CASCADE;
CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);


CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  price NUMERIC NOT NULL,
  featured BOOLEAN NOT NULL,
  category_name VARCHAR(255) NOT NULL
  category_id INTEGER NOT NULL REFERENCES categories (id)
);