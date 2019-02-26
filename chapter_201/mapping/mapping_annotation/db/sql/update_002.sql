INSERT INTO car_body (body_name)
VALUES ('sedan'), ('hatchback'), ('pickup'), ('van'), ('coupe');

INSERT INTO engine (engine_name)
VALUES ('petrol'), ('diesel'), ('gas');

INSERT INTO transmission (trans_name)
VALUES ('manual'), ('automatic'), ('robotic');

INSERT INTO cars (model, body_id, engine_id, trans_id)
VALUES ('BMW', 5, 2, 2), ('Volkswagen', 1, 1, 2), ('Lada', 2, 1, 1);
