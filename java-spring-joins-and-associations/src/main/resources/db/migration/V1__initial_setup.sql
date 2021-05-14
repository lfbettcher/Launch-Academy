CREATE TABLE archetypes (
  id SERIAL PRIMARY KEY,
  type VARCHAR(255) NOT NULL,
  hit_dice INTEGER NOT NULL,
  primary_stat VARCHAR NOT NULL
);

CREATE TABLE player_characters (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  archetype_id INTEGER NOT NULL REFERENCES archetypes(id),
  race VARCHAR(255) NOT NULL,
  background TEXT,
  level INTEGER NOT NULL
);