/*
  @author: Hello World
  @date: 2018/8/13 11:14

  175. 组合两个表

  表1: Person
+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键

表2: Address
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，
都需要基于上述两表提供 person 的以下信息：
FirstName, LastName, City, State
*/

/*
  由于要保证无论person是否有地址信息, 都要提供信息, 则不能用内连接,
  且要以person为主表
  而sql中默认联结为内连接, 当显示调用join关键字时, 要用on
*/
SELECT FirstName, LastName, City, State
FROM Person LEFT JOIN Address
ON Person.PersonId = Address.PersonId