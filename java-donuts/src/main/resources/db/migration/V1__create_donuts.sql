DROP TABLE IF EXISTS donuts;

CREATE TABLE donuts (
  id SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  vegan BOOLEAN NOT NULL
);

INSERT INTO donuts (type, description, vegan) VALUES ('Smoreo Bismark', 'Fluffy brioche filled with toasted marshmallow cream', false);
INSERT INTO donuts (type, description, vegan) VALUES ('Apple Cider Glazed', 'Fluffy VEGAN brioche topped with apple cider glaze', true);