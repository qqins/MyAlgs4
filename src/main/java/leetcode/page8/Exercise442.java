package leetcode.page8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hello World
 * @date: 2018/6/4 20:27
 * <p>
 * 442. 数组中重复的数据 --> 剑指offer: 面试题3
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现
 * 一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * <p>
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [2,3]
 */
public class Exercise442 {
    /**
     *由于数组中数字都是大于0的，将数组中元素所对应的下标的值取负值
     * 后面一旦发现该下标所对应的值为负，就可知该值为重复值
     */
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                list.add(index+1);
            }
            nums[index] *= -1;
        }
        return list;
    }
}
