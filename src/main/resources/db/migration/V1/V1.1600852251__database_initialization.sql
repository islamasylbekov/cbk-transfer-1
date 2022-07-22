CREATE TABLE IF NOT EXISTS ref_common_reference_type
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    code  VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ref_common_reference
(
    id        BIGSERIAL PRIMARY KEY,
    type_id   BIGINT       NOT NULL REFERENCES ref_common_reference_type (id),
    title     TEXT         NOT NULL,
    code      VARCHAR(255) NOT NULL,
    enabled   BOOLEAN DEFAULT TRUE,
    parent_id BIGINT REFERENCES ref_common_reference (id)
);

CREATE TABLE IF NOT EXISTS security_role
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    code  VARCHAR(255) NOT NULL,
    default_page VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee
(
    id                       BIGSERIAL PRIMARY KEY,
    created_at               TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at               TIMESTAMP WITHOUT TIME ZONE,
    login                    VARCHAR(255) NOT NULL UNIQUE,
    password                 VARCHAR(255) NOT NULL,
    surname                  VARCHAR(255) NOT NULL,
    name                     VARCHAR(255) NOT NULL,
    date_of_birth            DATE,
    enabled                  BOOLEAN DEFAULT TRUE,
    rank                     VARCHAR(255),
    password_expiration_time TIMESTAMP DEFAULT now(),
    patronymic               VARCHAR(255),
    role_id                  BIGINT REFERENCES security_role (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS m2m_employee_security_role
(
    employee_id      BIGINT REFERENCES employee (id)      NOT NULL,
    security_role_id BIGINT REFERENCES security_role (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS bank
(
    id                       BIGSERIAL PRIMARY KEY,
    created_at               TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at               TIMESTAMP WITHOUT TIME ZONE,
    name                     VARCHAR(255) NOT NULL,
    address                  VARCHAR(255) NOT NULL,
    money_count              BIGINT NOT NULL
);

ALTER TABLE employee
    ADD COLUMN bank_id BIGINT REFERENCES bank (id);

CREATE TABLE IF NOT EXISTS transfer
(
    id                       BIGSERIAL PRIMARY KEY,
    created_at               TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    updated_at               TIMESTAMP WITHOUT TIME ZONE,
    sender_name              VARCHAR(255) NOT NULL,
    sender_surname           VARCHAR(255) NOT NULL,
    sender_patronymic        VARCHAR(255),
    sender_bank_id           BIGINT REFERENCES bank (id) NOT NULL,
    recipient_name           VARCHAR(255) NOT NULL,
    recipient_surname        VARCHAR(255) NOT NULL,
    recipient_patronymic     VARCHAR(255),
    recipient_bank_id        BIGINT REFERENCES bank (id),
    money_count              BIGINT NOT NULL,
    code                     VARCHAR(255),
    status                   BIGINT REFERENCES ref_common_reference (id) NOT NULL,
    employee_id                 BIGINT REFERENCES employee (id)
);

INSERT INTO security_role (code, title, default_page)
VALUES ('ROLE_ADMIN', 'Администратор', '/employee'),
       ('CASH_DESK_EMPLOYEE', 'Сотрудник кассы', '/transfer');

CREATE EXTENSION IF NOT EXISTS pgcrypto;

insert into bank (name, address, money_count)
values ('Головной офис', 'Тоголок Молдо, 54а, Бишкек', 2151515151);

INSERT INTO employee (login, password, surname, name, role_id, bank_id)
VALUES  ('admin',crypt('123456', gen_salt('bf')), 'Adminov', 'Admin', 1,
        (SELECT id FROM bank WHERE name = 'Головной офис')),
        ('kassa',crypt('123456', gen_salt('bf')), 'Касса1', 'Касса1', 2,
        (SELECT id FROM bank WHERE name = 'Головной офис'));


INSERT INTO ref_common_reference_type(id, title, code)
VALUES (1, 'Статус', 'STATUS');

INSERT INTO ref_common_reference(title, code, type_id)
VALUES ('ОТПРАВЛЕНО', 'SENT', (SELECT id FROM ref_common_reference_type WHERE code = 'STATUS')),
       ('ПОЛУЧЕНО', 'RECEIVED', (SELECT id FROM ref_common_reference_type WHERE code = 'STATUS'));