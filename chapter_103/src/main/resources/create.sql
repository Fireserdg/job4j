CREATE DATABASE items_system;

\c items_system

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	role_name VARCHAR(30) NOT NULL
);

CREATE TABLE rules (
	id SERIAL PRIMARY KEY,
	rule_name VARCHAR(30) NOT NULL
);

CREATE TABLE states (
	id SERIAL PRIMARY KEY,
	state_item VARCHAR(30) NOT NULL
);

CREATE TABLE categories (
	id SERIAL PRIMARY KEY,
	category VARCHAR(30) NOT NULL
);

CREATE TABLE roles_rules (
	role_id INTEGER NOT NULL,
	rule_id INTEGER NOT NULL,
	PRIMARY KEY(role_id, rule_id),
	FOREIGN KEY (role_id) REFERENCES roles (id),
	FOREIGN KEY (rule_id) REFERENCES rules (id)
);

CREATE TABLE users (
 	id SERIAL PRIMARY KEY,
 	user_name VARCHAR(30) NOT NULL,
 	role_id INTEGER,
 	FOREIGN KEY (role_id) REFERENCES roles (id)
 );

CREATE TABLE items (
	id INTEGER PRIMARY KEY,
	item_name VARCHAR(30),
	category_id INTEGER,
	state_id INTEGER,
	FOREIGN KEY (id) REFERENCES users(id),
	FOREIGN KEY (category_id) REFERENCES categories (id),
	FOREIGN KEY (state_id) REFERENCES states(id)
);

CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	description TEXT,
	item_id INTEGER,
	FOREIGN KEY(item_id) REFERENCES items(id)
);

CREATE TABLE attachs (
	id SERIAL PRIMARY KEY,
	description TEXT,
	item_id INTEGER,
	FOREIGN KEY(item_id) REFERENCES items(id)
);

INSERT INTO roles (role_name) 
VALUES ('CEO'), ('Admin'), ('Worker'); 

INSERT INTO rules (rule_name) 
VALUES ('rules1'), ('rules2'), ('rules3');

INSERT INTO users (user_name, role_id) 
VALUES ('Alex' , 3), ('Bob', 3), ('Den', 2);

INSERT INTO roles_rules VALUES
(1, 1), (1, 2), (3, 1);

INSERT INTO categories (category)
VALUES ('category1'), ('category2'), ('category3');

INSERT INTO states (state_item)
VALUES ('state1'), ('state2'), ('state3');

INSERT INTO items
VALUES (1, 'item1', 3 , 2), (2, 'item2', 2, 2), (3, 'item3', 1, 3);

INSERT INTO comments (description, item_id)
VALUES ('comments1', 2), ('comments2', 1), ('comments3', 3);

INSERT INTO attachs (description, item_id)
VALUES ('attachs1', 3), ('attachs2', 1), ('attachs3', 2);