package swordoffer.chapter2;

/**
 * @author: Hello World
 * @date: 2018/8/14 20:16
 * <p>
 * 面试题11: 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Question11 {
    /**
     * 若array[mid]<=array[hi], 说明最小值在[lo,mid]之间
     * 若array[mid]>array[hi], 说明最小值在[mid+1,hi]之间
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = array.length - 1;
        int mid = 0;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (array[mid] <= array[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return array[lo];
    }
}
