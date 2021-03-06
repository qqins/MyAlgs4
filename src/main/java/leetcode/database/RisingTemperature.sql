/*
  @author: Hello World
  @date: 2018/8/17 16:14

  197. 上升的温度

  给定一个 Weather 表，编写一个 SQL 查询，来查找与之前（昨天的）
  日期相比温度更高的所有日期的 Id。

+---------+------------------+------------------+
| Id(INT) | RecordDate(DATE) | Temperature(INT) |
+---------+------------------+------------------+
|       1 |       2015-01-01 |               10 |
|       2 |       2015-01-02 |               25 |
|       3 |       2015-01-03 |               20 |
|       4 |       2015-01-04 |               30 |
+---------+------------------+------------------+

例如，根据上述给定的 Weather 表格，返回如下 Id:
+----+
| Id |
+----+
|  2 |
|  4 |
+----+
*/

/**
  方法1 显式内连接
  有INNER JOIN，形成的中间表为两个表经过ON条件过滤后的笛卡尔积
 */
SELECT
    weather.id AS 'Id'
FROM
    weather
        JOIN
    weather w ON DATEDIFF(weather.RecordDate, w.RecordDate) = 1
        AND weather.Temperature > w.Temperature
;

/**
  方法2 隐式内连接
  没有INNER JOIN，形成的中间表为两个表的笛卡尔积。
 */

SELECT
  weather.id AS 'Id'
FROM
  weather, weather w
WHERE DATEDIFF(weather.RecordDate, w.RecordDate) = 1
        AND weather.Temperature > w.Temperature