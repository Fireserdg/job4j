CREATE TABLE company (
id INTEGER NOT NULL,
name CHARACTER VARYING,
CONSTRAINT company_key PRIMARY KEY(id)
);

CREATE TABLE person
(
id INTEGER NOT NULL,
name CHARACTER VARYING,
company_id INTEGER,
CONSTRAINT person_key PRIMARY KEY(id)
);

INSERT INTO company VALUES 
(1, 'Samsung'), (2, 'Sony'), (3, 'Apple'),
(4, 'Microsoft'), (5,'Gazprom'), (6, 'Google');

INSERT INTO person VALUES
(1, 'Bob', 1), (2, 'Max', 2), (3, 'Den', 3),
(4, 'Jon', 5), (5, 'Alex', 2), (6, 'Bill', 4),
(7, 'Stiv', 3), (8, 'Nike', 2), (9, 'Ken', 6), 
(10, 'Klark', 3);

SELECT c.id, c.name, p.name FROM person AS p, company AS c WHERE p.company_id=c.id AND c.id <> 5 ORDER BY c.id;

SELECT c.name AS company, p.name AS person FROM company AS c, person AS p WHERE
c.id = p.company_id;

SELECT c.name AS company_name, MAX(tab1.ccc) AS persons 
FROM company AS c, (SELECT company_id, COUNT(company_id) ccc FROM person GROUP BY company_id) AS tab1
WHERE c.id = tab1.company_id
GROUP BY c.name
HAVING MAX(tab1.ccc)=(SELECT MAX(ccc) FROM (SELECT count(company_id) ccc FROM person GROUP BY company_id) as tab2);
