/*
  @author: Hello World
  @date: 2018/8/13 11:06

  176. 第二高的薪水
  编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary） 。

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
例如上述 Employee 表，SQL查询应该返回 200 作为第二高的薪水。
如果不存在第二高的薪水，那么查询应返回 null。

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
*/

--方法1
--LIMIT 1表示返回一行
--OFFSET 1 表示从第一行开始(不包括第一行), 即返回的是第二行
/*
   假设数据库表student存在13条数据。
   语句1：select * from student limit 9,4
   语句2：select * from student limit 4 offset 9
   语句1和2均返回表student的第10、11、12、13行
   语句2中的4表示返回4行，9表示从表的第十行开始

   若表中没有记录, 则返回null
 */
SELECT
    (SELECT DISTINCT
            Salary
        FROM
            Employee
        ORDER BY Salary DESC
        LIMIT 1 OFFSET 1) AS SecondHighestSalary

/*
  方法2
  IFNULL(expr1,expr2)
  如果expr1为null，那么就返回expr2,；否则返回expr1
 */
SELECT
    IFNULL(
      (SELECT DISTINCT Salary
       FROM Employee
       ORDER BY Salary DESC
        LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary