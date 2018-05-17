package leetcode.page1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hello World
 * @date: 2018/5/17 12:34
 *
 * 1,两数之和
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Exercise1 {
    public static void main(String[] args) {
        int[] nums = {2, 5, 5, 11};
        int[] res = twoSum1(nums, 10);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("没有这两个数");
    }

    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];

            /**
             * 顺序不能搞反，先确定map中含有这个元素，再去取值与i判断
             * 若先get，再containskey，当i=0，此时res=8，但是数组中没有这个元素，get就会
             * 空指针异常
             */
            if (map.containsKey(res) && map.get(res) != i) {
                return new int[]{i, map.get(res)};
            }
        }
        throw new IllegalArgumentException("没有这两个数");
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            if (map.containsKey(res)) {
                return new int[]{map.get(res), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }
}
