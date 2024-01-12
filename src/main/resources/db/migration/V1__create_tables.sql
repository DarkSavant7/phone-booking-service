CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255),
    created  TIMESTAMP DEFAULT NOW(),
    updated  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE roles
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT NOW(),
    updated TIMESTAMP DEFAULT NOW()
);

CREATE TABLE users_roles
(
    user_id BIGINT REFERENCES users (id),
    role_id INTEGER REFERENCES roles (id),
    created TIMESTAMP DEFAULT NOW(),
    updated TIMESTAMP DEFAULT NOW()
);

CREATE TABLE devices
(
    id           BIGSERIAL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model        VARCHAR(255) NOT NULL,
    created      TIMESTAMP DEFAULT NOW(),
    updated      TIMESTAMP DEFAULT NOW()
);

CREATE TABLE bookings
(
    id        BIGSERIAL PRIMARY KEY,
    device_id BIGINT REFERENCES devices (id),
    user_id   BIGINT REFERENCES users (id),
    end_time  TIMESTAMP,
    created   TIMESTAMP DEFAULT NOW(),
    updated   TIMESTAMP DEFAULT NOW()
);