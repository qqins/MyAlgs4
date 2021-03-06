/*
  @author: Hello World
  @date: 2018/8/14 16:02

  178. 分数排名
  编写一个 SQL 查询来实现分数排名。如果两个分数相同，则两个分数排名（Rank）相同。
  请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+

例如，根据上述给定的 Scores 表，你的查询应该返回（按分数从高到低排列）：
+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+
*/

/*
  方法1
 */
SELECT
  Score,
  @rank := @rank + (@prev <> (@prev := Score)) Rank
FROM
  Scores,
  (SELECT @rank := 0, @prev := -1) init
ORDER BY Score DESC

/*
  方法2
 */
SELECT
  Score,
  (SELECT count(*) FROM (SELECT DISTINCT Score s FROM Scores) tmp WHERE s >= Score) Rank
FROM Scores
ORDER BY Score DESC

/**
  方法3
 */
SELECT
    S1.score,
    COUNT( DISTINCT S2.score ) Rank
FROM
    Scores S1
    INNER JOIN Scores S2
    ON S1.score <= S2.score
GROUP BY
    S1.id
ORDER BY
    S1.score DESC;