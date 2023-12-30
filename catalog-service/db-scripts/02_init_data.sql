USE catalogdb;

INSERT INTO product_categories(id, name) VALUES(1, "Pizza");
INSERT INTO product_categories(id, name) VALUES(2, "Beverage");

INSERT INTO products(id, name, category_id) VALUES(1, "Margherita Pizza", 1);
INSERT INTO products(id, name, category_id) VALUES(2, "Cheese Pizza", 1);
INSERT INTO products(id, name, category_id) VALUES(3, "Cola", 2);
INSERT INTO products(id, name, category_id) VALUES(4, "Water", 2);