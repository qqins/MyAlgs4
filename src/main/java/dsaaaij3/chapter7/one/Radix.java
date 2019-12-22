package dsaaaij3.chapter7.one;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qin
 * @date 2019/10/26 17:41
 */
public class Radix {
    public static void radixSort(String[] arr, int stringLen) {
        final int len = 256;

        ArrayList<String>[] buckets = new ArrayList[len];

        for (int i = 0; i < len; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int pos = stringLen - 1; pos >= 0; pos--) {
            for (String s : arr) {
                buckets[s.charAt(pos)].add(s);
            }

            int idx = 0;
            for (ArrayList<String> thisBucket : buckets) {
                for (String s : thisBucket) {
                    arr[idx++] = s;
                }
                /**
                 * 每排完一次序，就将已排好的数据从buckets中清空，
                 * 否则外层再次循环时，第22行会将数据重复存入buckets中，
                 * 这样到最后buckets中会有pos*arr.length个数据，即所有元素都
                 * 重复存入了pos个，会造成arr的ArrayIndexOutOfBoundsException
                 */
                thisBucket.clear();

            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CIO720",
                "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
        System.out.println(Arrays.toString(arr));
        radixSort(arr, 7);
        System.out.println(Arrays.toString(arr));
    }
}
