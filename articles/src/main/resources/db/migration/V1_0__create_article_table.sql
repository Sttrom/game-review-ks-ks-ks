CREATE TABLE IF NOT EXISTS articles (
    id SERIAL PRIMARY KEY,
    author VARCHAR(255),
    date DATE,
    name VARCHAR(255),
    picture VARCHAR(255),
    text TEXT
);