-- ROLES
INSERT INTO roles (name)
VALUES ('ADMIN');
INSERT INTO roles (name)
VALUES ('USER');

-- USERS
INSERT INTO users (login, password, email)
VALUES ('admin', '$2a$12$vDxDrs8LygQnOMRrTWCThuaA0s7EnfFkXuK3wdwjKjREknl6gVtQW',
        'admin@example.com'); -- password: 12345678
INSERT INTO users (login, password, email)
VALUES ('user', '$2a$12$vDxDrs8LygQnOMRrTWCThuaA0s7EnfFkXuK3wdwjKjREknl6gVtQW', 'user@example.com');
-- password: 12345678

-- USERS_ROLES
INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE login = 'admin'), (SELECT id FROM roles WHERE name = 'ADMIN'));
INSERT INTO users_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE login = 'user'), (SELECT id FROM roles WHERE name = 'USER'));

-- DEVICES
INSERT INTO devices (manufacturer, model)
VALUES ('Samsung', 'Galaxy s8');
INSERT INTO devices (manufacturer, model)
VALUES ('Samsung', 'Galaxy s9');
INSERT INTO devices (manufacturer, model)
VALUES ('Motorola', 'Nexus 6');
INSERT INTO devices (manufacturer, model)
VALUES ('Oneplus', '9');
INSERT INTO devices (manufacturer, model)
VALUES ('Apple', 'iPhone 13');
INSERT INTO devices (manufacturer, model)
VALUES ('Apple', 'iPhone 12');
INSERT INTO devices (manufacturer, model)
VALUES ('Apple', 'iPhone 11');
INSERT INTO devices (manufacturer, model)
VALUES ('Apple', 'iPhone X');
INSERT INTO devices (manufacturer, model)
VALUES ('Nokia', '3310');
INSERT INTO devices (manufacturer, model)
VALUES ('Samsung', 'Galaxy s8');
