 CREATE DATABASE mаchine_storage;
 \c mаchine_storage

 CREATE TABLE car_body
 (
 	id SERIAL PRIMARY KEY,
 	body_name VARCHAR(40) NOT NULL
 ); 

 CREATE TABLE engine
 (
 	id SERIAL PRIMARY KEY,
 	engine_name VARCHAR(40) NOT NULL
 ); 

 CREATE TABLE transmission
 (
 	id SERIAL PRIMARY KEY,
 	trans_name VARCHAR(40) NOT NULL
 );
 --Поменять имя Модели
 CREATE TABLE car (
  	id SERIAL PRIMARY KEY,
  	model VARCHAR(40) NOT NULL,
  	car_body_id INTEGER,
  	engine_id INTEGER,
  	transmission_id INTEGER,
  	FOREIGN KEY(car_body_id) REFERENCES car_body(id),
  	FOREIGN KEY(engine_id) REFERENCES engine(id),
  	FOREIGN KEY(transmission_id) REFERENCES transmission(id)
 );

  INSERT INTO car_body (body_name) 
  VALUES ('sedan'), ('hatchback'), ('pickup'), ('van'), ('coupe');

  INSERT INTO engine (engine_name) 
  VALUES ('petrol'), ('diesel'), ('gas');

  INSERT INTO transmission (trans_name) 
  VALUES ('manual'), ('automatic'), ('robotic ');

  INSERT INTO car (model, car_body_id, engine_id, transmission_id)
  VALUES ('BMW', 5, 2, 2), ('Volkswagen', 1, 1, 2), ('Lada', 2, 1, 1);

  SELECT c.id AS id, c.model AS model, cb.body_name AS body, en.engine_name AS engine,
  tr.trans_name AS transmission FROM car AS c LEFT OUTER JOIN car_body AS cb 
  ON c.car_body_id = cb.id LEFT OUTER JOIN engine AS en ON c.engine_id=en.id
  LEFT OUTER JOIN transmission AS tr ON c.transmission_id=tr.id; 

  SELECT cb.body_name AS body, en.engine_name AS engine,
  tr.trans_name AS transmission 
  FROM car AS c RIGHT OUTER JOIN car_body AS cb ON c.car_body_id=cb.id
  FULL OUTER JOIN engine AS en ON c.engine_id= en.id
  FULL OUTER JOIN transmission AS tr ON c.transmission_id=tr.id 
  WHERE c.model is NULL;

  SELECT cb.body_name AS body FROM car AS c
  RIGHT OUTER JOIN car_body AS cb
  ON c.car_body_id=cb.id WHERE c.id IS NULL;

  SELECT en.engine_name AS engine FROM car AS c 
  RIGHT OUTER JOIN engine AS en
  ON c.engine_id=en.id WHERE c.id IS NULL;

  SELECT tr.trans_name AS transmission FROM car AS c 
  RIGHT OUTER JOIN transmission AS tr
  ON c.transmission_id=tr.id WHERE c.id IS NULL;