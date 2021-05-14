CREATE TABLE urls (
  id SERIAL PRIMARY KEY,
  url TEXT NOT NULL,
  description TEXT,
  media_type VARCHAR(255)
)