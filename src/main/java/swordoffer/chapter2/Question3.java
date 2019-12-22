package swordoffer.chapter2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hello World
 * @date: 2018/6/4 19:27
 * <p>
 * 面试题3: 数组中重复数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个
 * 数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度
 * 为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 */
public class Question3 {
    /**
     * 时间复杂度为O(n), 由于没有开辟多余空间, 其空间复杂度为O(1)
     *
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length < 1) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (numbers[i] < 0 || numbers[i] > length - 1) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    duplication[0] = numbers[i];
                    return true;
                }
                /**
                 * 把每个数字放到其对应的下标处
                 */
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        return false;
    }

    /**
     * 在不改动数组时
     *
     * @param numbers
     * @param length
     * @return
     */
    public static int getDuplication(final int[] numbers, int length) {
        int start = 1;
        int end = length - 1;
        while (end >= start) {
            int middle = (end - start) / 2 + start;
            int count = countRange(numbers, length, start, middle);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }
            if (count > (middle - start + 1)) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    public static int countRange(final int[] numbers, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] >= start && numbers[i] <= end) {
                ++count;
            }
        }
        return count;
    }

    public static boolean duplicate1(int[] numbers, int length, int[] duplication) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < length; i++) {
            if (map.containsValue(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            }
            map.put(i, numbers[i]);
        }
        return false;
    }

    public static boolean duplicate2(int[] numbers, int length, int[] duplication) {
        boolean[] res = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (res[numbers[i]]) {
                duplication[0] = numbers[i];
                return true;
            }
            res[numbers[i]] = true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 3, 1, 0, 2, 5, 3};
        int length = numbers.length;
        int[] duplication = new int[1];
        System.out.println(getDuplication(numbers, length));
//        System.out.println(duplicate(numbers, length, duplication));
//        System.out.println(duplication[0]);
    }
}
