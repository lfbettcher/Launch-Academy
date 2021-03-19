INSERT INTO bands(band_name, year_formed, history)
VALUES ('Band 1', 1999, 'History of Band 1'),
  ('Band 2', 2005, 'History of Band 2');

INSERT INTO albums(album_name, release_year, label, liner_notes, band_id)
VALUES ('Album 1', 2000, 'Label 1', 'Liner Notes 1', 1),
  ('Album 2', 2002, 'Label 2', 'Liner Notes 2', 1),
  ('Album 3', 2006, 'Label 3', 'Liner Notes 3', 2),
  ('Album 4', 2008, 'Label 4', 'Liner Notes 4', 2);

INSERT INTO songs(band_id, album_id, title, single)
VALUES (1, 1, 'Song Title 1', TRUE),
  (1, 1, 'Song Title 2', FALSE),
  (2, 2, 'Song Title 3', TRUE),
  (2, 2, 'Song Title 4', FALSE);

SELECT b.band_name, a.album_name, s.title
FROM bands b
  JOIN songs s ON b.id = s.band_id
  JOIN albums a ON s.album_id = a.id;