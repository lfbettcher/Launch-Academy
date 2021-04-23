CREATE TABLE blasters (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  recharge_time INT NOT NULL,
  attachment VARCHAR(255)
);