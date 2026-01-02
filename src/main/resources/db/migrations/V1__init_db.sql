CREATE TABLE Product
(
    ref   VARCHAR(255)     NOT NULL,
    name  VARCHAR(55)      NOT NULL,
    stock DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (ref)
);

ALTER TABLE Product
    ADD CONSTRAINT uc_product_name UNIQUE (name);