CREATE TABLE users (
    id IDENTITY NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(128) NOT NULL,
    last_seen TIMESTAMP
);

CREATE INDEX idx_email ON USERS (email);