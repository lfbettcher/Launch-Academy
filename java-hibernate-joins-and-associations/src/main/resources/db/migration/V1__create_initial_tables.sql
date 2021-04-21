CREATE TABLE articles (
  id SERIAL PRIMARY KEY,
  subject VARCHAR(255) NOT NULL,
  story TEXT NOT NULL
);

CREATE TABLE comments (
  id SERIAL PRIMARY KEY,
  article_id int NOT NULL REFERENCES articles(id),
  body TEXT
);