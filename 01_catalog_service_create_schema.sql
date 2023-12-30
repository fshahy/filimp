CREATE DATABASE IF NOT EXISTS catalogdb;
USE catalogdb;

CREATE TABLE IF NOT EXISTS product_categories (
        id int(11) NOT NULL AUTO_INCREMENT,
        name varchar(45) NOT NULL,
        PRIMARY KEY (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS products (
        id int(11) NOT NULL AUTO_INCREMENT,
        name varchar(45) NOT NULL,
        category_id int(11) NOT NULL,
        PRIMARY KEY (id),
        KEY fk_products_category_fk_idx (category_id),
        CONSTRAINT fk_products_category_fk FOREIGN KEY (category_id) REFERENCES product_categories (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;