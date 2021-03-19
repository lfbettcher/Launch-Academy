CREATE TABLE IF NOT EXISTS bands (
  id SERIAL PRIMARY KEY,
  band_name VARCHAR(255) NOT NULL,
  year_formed INT NOT NULL,
  history TEXT
);

CREATE TABLE IF NOT EXISTS albums (
  id SERIAL PRIMARY KEY,
  album_name VARCHAR(255) NOT NULL,
  release_year INT NOT NULL,
  label VARCHAR(255),
  liner_notes TEXT,
  band_id INT NOT NULL REFERENCES bands(id)
);

CREATE TABLE IF NOT EXISTS songs (
  id SERIAL PRIMARY KEY,
  band_id INT NOT NULL REFERENCES bands(id),
  album_id INT NOT NULL REFERENCES albums(id),
  title VARCHAR(255) NOT NULL,
  single BOOLEAN NOT NULL
);