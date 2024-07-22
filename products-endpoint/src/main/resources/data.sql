DELETE FROM products.product WHERE id = '1000';
DELETE FROM products.product WHERE id = '1001';
DELETE FROM products.product WHERE id = '1002';
DELETE FROM products.product WHERE id = '1004';

INSERT into products.product (id,name,price, image) VALUES ('1000', 'Apple', '5', 'apple.jpeg');
INSERT into products.product (id,name,price, image) VALUES ('1001', 'Chicken', '20', 'chicken.jpeg');
INSERT into products.product (id,name,price, image) VALUES ('1002', 'Bicycle', '200', 'bicycle.webp');
INSERT into products.product (id,name,price, image) VALUES ('1004', 'Onion', '6', 'onion.JPG');