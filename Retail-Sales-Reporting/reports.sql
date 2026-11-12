-- Day 06: Retail Sales Reporting | MySQL 8.4
USE retail_sales_db;

-- 1. Top 5 products by revenue
SELECT p.product_id, p.product_name,
       ROUND(SUM(oi.quantity * oi.unit_price),2) AS total_revenue
FROM products p JOIN order_items oi ON oi.product_id=p.product_id
GROUP BY p.product_id,p.product_name
ORDER BY total_revenue DESC LIMIT 5;

-- 2. Month-wise sales
SELECT DATE_FORMAT(o.order_date,'%Y-%m') AS sales_month,
       ROUND(SUM(oi.quantity*oi.unit_price),2) AS monthly_sales
FROM orders o JOIN order_items oi ON oi.order_id=o.order_id
GROUP BY DATE_FORMAT(o.order_date,'%Y-%m')
ORDER BY sales_month;

-- 3. Customers with more than three orders (HAVING)
SELECT c.customer_id,c.customer_name,COUNT(o.order_id) AS total_orders
FROM customers c JOIN orders o ON o.customer_id=c.customer_id
GROUP BY c.customer_id,c.customer_name
HAVING COUNT(o.order_id)>3;

-- 4. Average basket value per city: aggregate each order before averaging.
SELECT c.city,ROUND(AVG(t.basket_value),2) AS average_basket_value
FROM customers c
JOIN orders o ON o.customer_id=c.customer_id
JOIN (
 SELECT order_id,SUM(quantity*unit_price) AS basket_value
 FROM order_items GROUP BY order_id
) t ON t.order_id=o.order_id
GROUP BY c.city ORDER BY c.city;

-- 5. Orders without a payment record (LEFT JOIN)
SELECT o.order_id,c.customer_name,o.order_date
FROM orders o JOIN customers c ON c.customer_id=o.customer_id
LEFT JOIN payments p ON p.order_id=o.order_id
WHERE p.payment_id IS NULL;

-- 6. Customers with no orders
SELECT c.customer_id,c.customer_name,c.city
FROM customers c LEFT JOIN orders o ON o.customer_id=c.customer_id
WHERE o.order_id IS NULL;

-- 7. Product-category revenue share
SELECT p.category,
 ROUND(SUM(oi.quantity*oi.unit_price),2) AS category_revenue,
 ROUND(SUM(oi.quantity*oi.unit_price)*100.0/
       (SELECT SUM(quantity*unit_price) FROM order_items),2) AS revenue_share_percent
FROM products p JOIN order_items oi ON oi.product_id=p.product_id
GROUP BY p.category ORDER BY category_revenue DESC;

-- 8. Day with highest sales
SELECT o.order_date AS sales_day,
       ROUND(SUM(oi.quantity*oi.unit_price),2) AS daily_sales
FROM orders o JOIN order_items oi ON oi.order_id=o.order_id
GROUP BY o.order_date
ORDER BY daily_sales DESC,o.order_date ASC LIMIT 1;

-- Function demonstration: UPPER, CONCAT, DATEDIFF, DATE_FORMAT, ROUND
SELECT c.customer_id,
 UPPER(c.customer_name) AS customer_name_upper,
 CONCAT(c.customer_name,' - ',c.city) AS customer_display,
 MIN(o.order_date) AS first_order_date,
 DATEDIFF(CURDATE(),MIN(o.order_date)) AS days_since_first_order,
 DATE_FORMAT(MIN(o.order_date),'%d-%b-%Y') AS first_order_formatted,
 ROUND(AVG(oi.quantity*oi.unit_price),2) AS average_item_line_value
FROM customers c
LEFT JOIN orders o ON o.customer_id=c.customer_id
LEFT JOIN order_items oi ON oi.order_id=o.order_id
GROUP BY c.customer_id,c.customer_name,c.city
ORDER BY c.customer_id;

-- NULL behaviour: Meena has no orders. LEFT JOIN returns one row with NULL order_id.
SELECT c.customer_name,COUNT(*) AS count_star,
 COUNT(o.order_id) AS count_order_id,
 SUM(o.order_id) AS sum_order_id,AVG(o.order_id) AS avg_order_id
FROM customers c LEFT JOIN orders o ON o.customer_id=c.customer_id
WHERE c.customer_name='Meena Devi'
GROUP BY c.customer_id,c.customer_name;
-- Expected: COUNT(*)=1; COUNT(order_id)=0; SUM=NULL; AVG=NULL.
-- COUNT(*) counts rows; COUNT(column), SUM and AVG ignore NULL values.

-- WHERE vs HAVING: WHERE filters source rows before grouping; HAVING filters groups.
SELECT c.customer_name,COUNT(o.order_id) AS october_orders
FROM customers c JOIN orders o ON o.customer_id=c.customer_id
WHERE o.order_date>='2026-10-01' AND o.order_date<'2026-11-01'
GROUP BY c.customer_id,c.customer_name
HAVING COUNT(o.order_id)>=2
ORDER BY october_orders DESC;
