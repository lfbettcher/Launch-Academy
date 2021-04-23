CREATE TABLE laser_cannons (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  recharge_time INT NOT NULL,
  fire_rate INT NOT NULL
);