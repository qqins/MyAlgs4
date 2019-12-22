package swordoffer.chapter6;

/**
 * @author: Hello World
 * @date: 2018/9/2 16:16
 * <p>
 * 面试题53：在排序数组中查找数字
 * 统计一个数字在排序数组中出现的次数。
 * 例如，输入排序数组{1,2,3,3,3,3,4,5}和数字3
 * 输出4
 */
public class Question53 {
    public static int getNumberOfK(int[] array, int k) {
        int first = binarySearch(array, k);
        int last = binarySearch(array, k + 1);
        /**
         * 对于k=3, array为以下时，应返还0
         * 2 2 2 2 2 2
         * 4 4 4 4 4 4
         */
        return (first == array.length || array[first] != k) ? 0 : last - first;
    }

    private static int binarySearch(int[] array, int k) {
        int start = 0;
        int end = array.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (array[mid] >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    /**
     * 书中的解法，更直观
     */
    public static int getNumberOfKByBook(int[] array, int k) {
        int first = getFirstK(array, k);
        int last = getLastK(array, k);
        return (first != -1 && last != -1) ? last - first + 1 : 0;
    }

    private static int getFirstK(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] < k) {
                start = mid + 1;
            } else if (array[mid] > k) {
                end = mid - 1;
            } else {
                if (mid > 0 && array[mid - 1] != k || mid == 0) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    private static int getLastK(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] < k) {
                start = mid + 1;
            } else if (array[mid] > k) {
                end = mid - 1;
            } else {
                if (mid < array.length - 1 && array[mid + 1] != k || mid == array.length - 1) {
                    return mid;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 面试题53.2:0~n-1中缺失的数字
     * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字的范围都在0~n-1之内。
     * 在范围0~n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字
     */
    public static int getMissingNumber(int[] array, int length) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == mid) {
                start = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] == mid - 1) {
                    return mid;
                }
                end = mid - 1;
            }
        }
        if (start == length) {
            return length;
        }
        return -1;
    }

    /**
     * 面试题53.3：数组中数值和下标相等的元素
     * 题目：假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实
     * 现一个函数找出数组中任意一个数值等于其下标的元素。例如，在数组
     * {-3, -1, 1, 3, 5}中，数字3和它的下标相等。
     */
    public static int getNumberSameAsIndex(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] > mid) {
                end = mid - 1;
            } else if (array[mid] < mid) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /*int[] array = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfKByBook(array, 3));*/

        /*int[] array = {0, 1, 2, 3, 4, 5};
        System.out.println(getMissingNumber(array, 6));*/

        int[] array={ -3, -1, 1, 3, 5 };
        System.out.println(getNumberSameAsIndex(array));
    }
}
