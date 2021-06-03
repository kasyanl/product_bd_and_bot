CREATE TABLE product
(
    id           INTEGER,
    category     VARCHAR(255)            NOT NULL,
    name         VARCHAR(255)            NOT NULL,
    price        DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    discount     DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    total_volume DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    actual_price DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    data         DATETIME                NOT NULL
);

CREATE TABLE person
(
    id       INTEGER,
    login    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE productOfDelete
(
    id           INTEGER,
    category     VARCHAR(255)            NOT NULL,
    name         VARCHAR(255)            NOT NULL,
    price        DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    discount     DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    actual_price DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    total_volume DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    data         DATETIME                NOT NULL
);

CREATE TABLE buyproduct
(
    id           INTEGER,
    name         VARCHAR(255)            NOT NULL,
    actual_price DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    quantity     DOUBLE(10, 3) DEFAULT 0 NOT NULL,
    total_price  DOUBLE(10, 3) DEFAULT 0 NOT NULL
)