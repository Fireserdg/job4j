CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR(50) NOT NULL
);

INSERT INTO users (name) VALUES ('Bob'), ('Alex'), ('Jon'),
 ('Ken'), ('Den'), ('Bob'), ('Sam'), ('Alex'), ('Bob');

SELECT*FROM users;

DELETE FROM users WHERE id NOT IN 
(SELECT MIN(id) FROM users GROUP BY name);

SELECT*FROM users;

DROP TABLE users;