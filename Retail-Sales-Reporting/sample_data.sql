-- Day 06: Retail Sales Reporting | MySQL 8.4
CREATE DATABASE IF NOT EXISTS retail_sales_db;
USE retail_sales_db;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE customers (
 customer_id INT AUTO_INCREMENT PRIMARY KEY,
 customer_name VARCHAR(100) NOT NULL,
 city VARCHAR(60) NOT NULL,
 email VARCHAR(120) UNIQUE
) ENGINE=InnoDB;

CREATE TABLE products (
 product_id INT AUTO_INCREMENT PRIMARY KEY,
 product_name VARCHAR(100) NOT NULL,
 category VARCHAR(60) NOT NULL,
 price DECIMAL(10,2) NOT NULL CHECK(price >= 0)
) ENGINE=InnoDB;

CREATE TABLE orders (
 order_id INT AUTO_INCREMENT PRIMARY KEY,
 customer_id INT NOT NULL,
 order_date DATE NOT NULL,
 FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
) ENGINE=InnoDB;

CREATE TABLE order_items (
 order_item_id INT AUTO_INCREMENT PRIMARY KEY,
 order_id INT NOT NULL,
 product_id INT NOT NULL,
 quantity INT NOT NULL CHECK(quantity > 0),
 unit_price DECIMAL(10,2) NOT NULL CHECK(unit_price >= 0),
 FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
 FOREIGN KEY(product_id) REFERENCES products(product_id)
) ENGINE=InnoDB;

-- Extra table to support the required LEFT JOIN for orders without payment.
CREATE TABLE payments (
 payment_id INT AUTO_INCREMENT PRIMARY KEY,
 order_id INT NOT NULL UNIQUE,
 payment_date DATE,
 amount DECIMAL(10,2) NOT NULL CHECK(amount >= 0),
 payment_status VARCHAR(20) NOT NULL,
 FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO customers VALUES
(1,'Arun Kumar','Chennai','arun@example.com'),
(2,'Priya Sharma','Madurai','priya@example.com'),
(3,'Karthik Raj','Chennai','karthik@example.com'),
(4,'Divya Nair','Coimbatore','divya@example.com'),
(5,'Ramesh Babu','Madurai','ramesh@example.com'),
(6,'Meena Devi','Coimbatore','meena@example.com');

INSERT INTO products VALUES
(1,'Wireless Mouse','Accessories',500.00),
(2,'Keyboard','Accessories',1200.00),
(3,'USB-C Hub','Accessories',1800.00),
(4,'Monitor','Electronics',12000.00),
(5,'Laptop Stand','Furniture',1500.00),
(6,'Webcam','Electronics',2500.00);

INSERT INTO orders VALUES
(1,1,'2026-10-01'),(2,1,'2026-10-05'),(3,1,'2026-10-12'),
(4,1,'2026-11-02'),(5,2,'2026-10-03'),(6,2,'2026-11-05'),
(7,3,'2026-10-10'),(8,4,'2026-11-07'),(9,5,'2026-11-08');

INSERT INTO order_items VALUES
(1,1,1,2,500.00),(2,1,2,1,1200.00),
(3,2,4,1,12000.00),(4,2,1,1,500.00),
(5,3,3,2,1800.00),(6,4,4,1,12000.00),
(7,5,5,2,1500.00),(8,5,1,1,500.00),
(9,6,6,1,2500.00),(10,7,2,2,1200.00),
(11,8,4,1,12000.00),(12,9,3,1,1800.00);

INSERT INTO payments VALUES
(1,1,'2026-10-01',2200.00,'PAID'),
(2,2,'2026-10-05',12500.00,'PAID'),
(3,3,'2026-10-12',3600.00,'PAID'),
(4,4,'2026-11-02',12000.00,'PAID'),
(5,5,'2026-10-03',3500.00,'PAID'),
(6,6,'2026-11-05',2500.00,'PENDING'),
(7,7,'2026-10-10',2400.00,'PAID'),
(8,8,'2026-11-07',12000.00,'PAID');
-- Order 9 intentionally has no payment record.
