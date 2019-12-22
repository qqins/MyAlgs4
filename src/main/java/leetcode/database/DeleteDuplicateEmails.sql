/*
  @author: Hello World
  @date: 2018/8/14 14:55

  196. 删除重复的电子邮箱

  编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，
  重复的邮箱里只保留 Id 最小 的那个。

+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+

Id 是这个表的主键。
例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:
+----+------------------+
| Id | Email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
*/

/*
  方法1
  最后的as p 不能省, 否则报错
  p作为临时表的名字
 */
DELETE FROM
Person
WHERE
Id NOT IN (SELECT Id
           FROM
            (SELECT MIN(Id) AS Id
             FROM Person
             GROUP BY Email
            ) AS p
          );

/*
  方法2
  p1, p2 为Person的别名, 省略了AS
 */
DELETE p1
FROM Person p1, Person p2
WHERE p1.Email = p2.Email AND p1.Id > p2.Id

/*
  上述方法也可以如下写
 */
DELETE p1
FROM Person p1 JOIN Person p2
ON p1.Email = p2.Email AND p1.id > p2.id