CREATE TABLE categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

ALTER TABLE products ADD COLUMN category_id INT REFERENCES categories(id);