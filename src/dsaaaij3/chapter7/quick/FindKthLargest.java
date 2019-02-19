package dsaaaij3.chapter7.quick;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Hello World
 * @date: 2018/12/24 14:49
 * <p>
 * 求数组中第k大的数
 */
public class FindKthLargest {
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        return findKthLargest(nums, 0, nums.length - 1, k - 1);
    }

    private static int findKthLargest(int[] nums, int start, int end, int k) {
        if (start > end) {
            return Integer.MAX_VALUE;
        }

        int pivot = nums[start];
        int left = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > pivot) {
                swap(nums, ++left, i);
            }
        }
        swap(nums, left, start);

        /*int pivot = nums[end];
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] > pivot) {
                swap(nums, left++, i);
            }
        }
        swap(nums, left, end);*/

        if (left == k) {
            return nums[left];
        } else if (left < k) {
            return findKthLargest(nums, left + 1, end, k);
        } else {
            return findKthLargest(nums, start, left - 1, k);
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(10);
        }
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(findKthLargest(array, 4));
    }
}
