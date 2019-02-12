CREATE TABLE IF NOT EXISTS items
  (id SERIAL PRIMARY KEY NOT NULL,
  description VARCHAR(255) NOT NULL,
  created TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  done BOOLEAN NOT NULL);

INSERT INTO items (description, created, done)
VALUES ('test_desc1', NOW(), false),('test_desc2', NOW(), false), ('test_desc2', NOW(), true);
