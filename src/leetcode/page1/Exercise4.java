package leetcode.page1;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Hello World
 * @date: 2018/5/21 18:55
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 中位数是 2.0
 * <p>
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 中位数是 (2 + 3)/2 = 2.5
 */
public class Exercise4 {
    /**
     * 将两个数组中较小的存在链表中，由于要取中位数，所以只需循环一半
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int sumLength = nums1.length + nums2.length;
        LinkedList<Integer> list = new LinkedList<>();
        int pos1 = 0, pos2 = 0;
        while (list.size() <= sumLength / 2) {
            if (pos1 >= nums1.length) {
                list.add(nums2[pos2++]);
                continue;
            }
            if (pos2 >= nums2.length) {
                list.add(nums1[pos1++]);
                continue;
            }
            if (nums1[pos1] < nums2[pos2]) {
                list.add(nums1[pos1++]);
            } else {
                list.add(nums2[pos2++]);
            }
        }
        if (sumLength % 2 == 0) {
            result = (list.pollLast() + list.pollLast()) / (2 * 1.0);
        } else {
            result = list.getLast();
        }
        return result;
    }

    /**
     * https://www.jianshu.com/p/7c990b12a82a
     * 参考答案，复杂度为O(log(m+n))
     *
     * @param A
     * @param B
     * @return
     */
    public static double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // to ensure m<=n
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                // i is too small
                iMin = iMin + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                // i is too big
                iMax = iMax - 1;
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double result = findMedianSortedArrays1(nums1, nums2);
        System.out.println(result);
    }
}
