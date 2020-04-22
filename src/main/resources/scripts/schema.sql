CREATE TABLE USERS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL
);

CREATE TABLE PRODUCTS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR NOT NULL,
    DESCRIPTION VARCHAR NOT NULL,
    BASE_PRICE DECIMAL NOT NULL,
    TAX_RATE DECIMAL NOT NULL,
    PRODUCT_STATUS VARCHAR NOT NULL,
    INVENTORY_QUANTITY INT
    );
