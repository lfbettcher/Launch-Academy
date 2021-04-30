CREATE TABLE rsvps (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    contact VARCHAR(15) NOT NULL
);