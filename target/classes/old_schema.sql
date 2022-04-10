CREATE TABLE customer (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

CREATE TABLE product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price NUMERIC(20, 2)
);

CREATE TABLE request (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    customer_id INTEGER REFERENCES customer (id),
    date TIMESTAMP,
    total NUMERIC(20, 2)
);

CREATE TABLE product_request (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    product_id INTEGER REFERENCES product (id),
    request_id INTEGER REFERENCES request (id),
    quantity INTEGER
);