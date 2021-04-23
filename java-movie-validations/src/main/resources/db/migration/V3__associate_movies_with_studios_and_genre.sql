ALTER TABLE movies ADD FOREIGN KEY (genre_id) REFERENCES genres(id);
ALTER TABLE movies ADD FOREIGN KEY (studio_id) REFERENCES studios(id);