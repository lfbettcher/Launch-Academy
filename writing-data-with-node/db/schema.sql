DROP TABLE IF EXISTS journal_entries;
CREATE TABLE journal_entries (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  body TEXT NOT NULL,
  entered_at TIMESTAMP NOT NULL
)
