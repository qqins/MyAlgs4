/*
  @author: Hello World
  @date: 2018/8/14 16:45

  183. 从不订购的客户
  某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，
  找出所有从不订购任何东西的客户。

Customers 表：
+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+

Orders 表：
+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+

例如给定上述表格，你的查询应返回：
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
*/

SELECT Customers.name AS 'Customers'
FROM Customers
WHERE Customers.id NOT IN
(
    SELECT CustomerId FROM Orders
);


SELECT A.Name AS 'Customers'
FROM Customers A LEFT JOIN Orders B
ON  A.Id = B.CustomerId
WHERE B.CustomerId IS NULL