-- liquibase formatted sql

-- changeset codenation:1.0 dbms:mysql
INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('standard');

-- changeset codenation:1.1 dbms:mysql
INSERT INTO user (email, last_seen, name, password_hash)
VALUES('admin@codenation.com', NOW(), 'Administrator', 'a1b2c3d4e5f6');

INSERT INTO user (email, last_seen, name, password_hash)
VALUES('standard@codenation.com', NOW(), 'Standard User', '1a2b3c4d5e6f');

-- changeset codenation:1.2 dbms:mysql
INSERT INTO environment (name) VALUES ('Produção');
INSERT INTO environment (name) VALUES ('Homologação');
INSERT INTO environment (name) VALUES ('Dev');

-- changeset codenation:1.3 dbms:mysql
INSERT INTO level (name) VALUES ('Error');
INSERT INTO level (name) VALUES ('Warning');
INSERT INTO level (name) VALUES ('Debug');

-- changeset codenation:1.4 dbms:mysql
INSERT INTO user_role (role_id, user_id) VALUES (1,1);
INSERT INTO user_role (role_id, user_id) VALUES (2,2);

-- changeset codenation:1.5 dbms:mysql
INSERT INTO log (description, details, origin, status, timestamp, title, environment_id, level_id, user_id)
VALUES ('acceleration.Service.AddCandidate: <forbidden>', 'Esses sao os detalhes do erro', 'Origem do Log', 1, NOW(), 'Titulo', 1, 1, 1);

INSERT INTO log (description, details, origin, status, timestamp, title, environment_id, level_id, user_id)
VALUES ('acceleration.Service.AddCandidate: <Not Found>', 'Esses sao os detalhes do erro', '127.0.0.1', 1, NOW(), 'Titulo 2', 2, 2, 2);

