DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    item_name VARCHAR NOT NULL,
    item_quantity INTEGER NOT NULL,
    gluten_free BOOLEAN NOT NULL,
    image_url VARCHAR NOT NULL
);