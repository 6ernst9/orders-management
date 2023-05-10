CREATE TABLE package
(
    Id       SERIAL NOT NULL,
    clientId  BIGINT,
    productId BIGINT,
    price     FLOAT,
    quantity  INTEGER,
    CONSTRAINT pk_order PRIMARY KEY (Id)
);

CREATE TABLE client
(
    id    SERIAL NOT NULL,
    name  VARCHAR(255),
    email VARCHAR(255),
    age   INTEGER,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE product
(
    id       SERIAL NOT NULL,
    name     VARCHAR(255),
    price    FLOAT,
    quantity INTEGER,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE bill
(
    Id        SERIAL NOT NULL,
    datee     VARCHAR(255),
    time      VARCHAR(255),
    clientId  BIGINT,
    productId BIGINT,
    price     FLOAT,
    quantity  INTEGER,
    CONSTRAINT pk_bill PRIMARY KEY (Id)
);

ALTER TABLE package
ADD CONSTRAINT fk_product
FOREIGN KEY (productid)
REFERENCES product (id);

ALTER TABLE package
ADD CONSTRAINT fk_client
FOREIGN KEY (clientid)
REFERENCES client (id);