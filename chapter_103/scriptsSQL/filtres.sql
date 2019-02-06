CREATE DATABASE products;
\c products

CREATE TABLE type 
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE product
(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	type_id INTEGER,
	expired_date DATE NOT NULL,
	price INTEGER NOT NULL,
	FOREIGN KEY(type_id) REFERENCES type(id)
);

INSERT INTO type (name) VALUES ('Сыр'), ('Мясо'), ('Молоко'),
 ('Мороженное'), ('Фрукты'), ('Овощи');

INSERT INTO product (name, type_id, expired_date, price)
VALUES 
('Маасдам', 1, '2018-09-30', 150), ('Рокфор', 1, '2018-10-15', 166),
('Шоколадное мороженное', 4, '2018-09-25', 50),
('Сливочное мороженное', 4, '2018-10-02', 45),
('Говядина', 2, '2018-09-28', 350),
('Свинина', 2, '2018-10-02', 277),
('Баранина', 2, '2018-10-07', 380),
('Домик в деревне', 3, '2018-09-28', 55),
('Веселый молочник', 3, '2018-10-11', 61),
('Яблоки', 5, '2018-10-03', 100),
('Груши', 5, '2018-10-05', 95),
('Капуста', 6, '2018-09-28', 35),
('Морковка', 6, '2018-09-30', 78),
('Лук', 6, '2018-10-15', 28),
('Базилик', 6, '2018-10-11', 25),
('Петрушка', 6, '2018-09-27', 20),
('Перец красный', 6, '2018-10-11', 95),
('Перец желтый', 6, '2018-10-04', 111),
('Огурцы', 6, '2018-09-30', 38),
('Помидоры', 6, '2018-10-11', 100),
('Баклажан', 6, '2018-10-18', 75),
('Цукини', 6, '2018-09-28', 28);

SELECT p.id, p.name FROM product AS p INNER JOIN type AS t 
ON p.type_id=t.id WHERE t.name='Сыр';
 
SELECT*FROM product WHERE name LIKE '%мороженное%';
 
SELECT id, name, expired_date FROM product WHERE expired_date BETWEEN '2018-10-01' 
AND '2018-10-31';

SELECT*FROM product WHERE price = (SELECT MAX(price) FROM product);
 
SELECT t.name AS type_name, COUNT(p.type_id) AS count_products FROM product AS p
INNER JOIN type AS t ON p.type_id=t.id GROUP BY t.name;

SELECT p. id, p.name AS name_product, t.name as type_name FROM product AS p 
INNER JOIN type AS t ON p.type_id=t.id WHERE t.name IN('Сыр', 'Молоко');

SELECT t.name as type_name FROM product as p INNER JOIN type as t ON p.type_id=t.id
GROUP BY t.name HAVING COUNT(p.type_id) < 10;

SELECT p.id, p.name AS name_product, t.name type_product
FROM product AS p INNER JOIN type AS t ON p.type_id = t.id;