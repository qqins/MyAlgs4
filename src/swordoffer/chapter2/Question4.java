package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/6/10 16:31
 * <p>
 * 面试题4：二维数组中的查找
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Question4 {
    /**
     * java中获取二维数组中行和列的长度：
     * 行：array.length
     * 列：array[0].length
     */
    public boolean find(int target, int[][] array) {
        //行
        int row = 0;
        //列
        int column = array[0].length - 1;
        while (row < array.length && column >= 0) {
            if (array[row][column] == target) {
                return true;
            } else if (array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }
}
