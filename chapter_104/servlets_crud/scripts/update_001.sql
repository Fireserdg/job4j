CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL, login VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL,
  create_date TIMESTAMP NOT NULL, role VARCHAR(100) NOT NULL,
  country_name VARCHAR(100) NOT NULL, city_name VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS country
  (id SERIAL PRIMARY KEY, country_name VARCHAR(30) NOT NULL);

CREATE TABLE IF NOT EXISTS city
  (id SERIAL PRIMARY KEY, city_name
  VARCHAR(40) NOT NULL, country_id INTEGER REFERENCES country(id));

INSERT INTO users (name, login, password, email, create_date, role, country_name, city_name)
VALUES ('Admin', 'login', '123', 'serdg1984@yandex.ru', NOW() , 'ADMIN', 'Russia', 'Moscow');