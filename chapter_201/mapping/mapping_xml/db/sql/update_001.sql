 CREATE TABLE IF NOT EXISTS CAR_BODY_XML
 (
 	id SERIAL PRIMARY KEY,
 	body_name VARCHAR(40) NOT NULL
 ); 

 CREATE TABLE IF NOT EXISTS ENGINE_XML
 (
 	id SERIAL PRIMARY KEY,
 	engine_name VARCHAR(40) NOT NULL
 ); 

 CREATE TABLE IF NOT EXISTS TRANSMISSION_XML
 (
 	id SERIAL PRIMARY KEY,
 	trans_name VARCHAR(40) NOT NULL
 );

 CREATE TABLE IF NOT EXISTS CARS_XML (
  	id SERIAL PRIMARY KEY,
  	model VARCHAR(40) NOT NULL,
  	body_id INTEGER NOT NULL,
  	engine_id INTEGER NOT NULL,
  	trans_id INTEGER NOT NULL,
  	FOREIGN KEY(body_id) REFERENCES CAR_BODY_XML (id),
  	FOREIGN KEY(engine_id) REFERENCES ENGINE_XML (id),
  	FOREIGN KEY(trans_id) REFERENCES TRANSMISSION_XML (id)
 );