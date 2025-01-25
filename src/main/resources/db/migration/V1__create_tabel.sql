-- Создание таблицы для сущности Role с автоинкрементом
CREATE TABLE role (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL UNIQUE
);

-- Создание таблицы для сущности User с автоинкрементом
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

-- Создание связующей таблицы для ManyToMany отношения между User и Role
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);