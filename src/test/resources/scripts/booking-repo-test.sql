-- Clean up the tables.
TRUNCATE TABLE bookings;
TRUNCATE TABLE devices;
TRUNCATE TABLE users;

-- Inserting into users
INSERT INTO users (id, login)
VALUES (1, 'TestUser');

-- Inserting into devices
INSERT INTO devices (id, manufacturer, model)
VALUES (1, 'Apple', 'iPhone X');

-- Inserting initial record into bookings
INSERT INTO bookings (id, device_id, user_id, created, updated)
VALUES (1, 1, 1, NOW(), NOW());