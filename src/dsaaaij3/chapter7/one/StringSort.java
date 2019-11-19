package dsaaaij3.chapter7.one;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qin
 * @date 2019/10/29 22:49
 */
public class StringSort {
    /**
     * 反向填充
     *
     * @param arr
     * @param strLength
     */
    public static void radixCountStrSort(String[] arr, int strLength) {
        final int bucket = 256;
        String[] temp = new String[arr.length];
        for (int d = strLength - 1; d >= 0; d--) {
            int[] count = new int[bucket];
            //count下标对应的字母中填的值为该字母的最大位次
            for (String s : arr) {
                count[s.charAt(d)]++;
            }
            for (int r = 1; r < bucket; r++) {
                count[r] += count[r - 1];
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                temp[count[arr[i].charAt(d)] - 1] = arr[i];
                count[arr[i].charAt(d)]--;
            }
            System.arraycopy(temp, 0, arr, 0, arr.length);
        }
    }

    /**
     * 正向填充
     *
     * @param arr
     * @param strLength
     */
    public static void radixCountStrSort2(String[] arr, int strLength) {
        final int bucket = 256;
        String[] buffer = new String[arr.length];
        String[] in = arr;
        String[] out = buffer;
        for (int d = strLength - 1; d >= 0; d--) {
            int[] count = new int[bucket + 1];
            //count下标对应的字母中填的值为该字母的起始位次
            for (String s : in) {
                count[s.charAt(d) + 1]++;
            }
            for (int r = 0; r < count.length - 1; r++) {
                count[r + 1] += count[r];
            }
            for (String s : in) {
                out[count[s.charAt(d)]++] = s;
            }
            String[] temp = in;
            in = out;
            out = temp;
        }
        //将in中的数据复制到out中
        if (strLength % 2 == 1) {
            System.arraycopy(in, 0, out, 0, arr.length);
        }
    }

    /**
     * 变长字符串排序
     */
    public static void changeStringSort(String[] arr, int maxLen) {
        final int bucket = 256;

        ArrayList<String>[] wordsByLength = new ArrayList[maxLen + 1];
        ArrayList<String>[] buckets = new ArrayList[bucket];

        for (int i = 0; i < wordsByLength.length; i++) {
            wordsByLength[i] = new ArrayList<>();
        }

        for (int i = 0; i < bucket; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (String s : arr) {
            wordsByLength[s.length()].add(s);
        }

        int index = 0;
        for (ArrayList<String> wordList : wordsByLength) {
            for (String s : wordList) {
                arr[index++] = s;
            }
        }

        int startingIndex = arr.length;
        for (int pos = maxLen - 1; pos >= 0; pos--) {
            startingIndex -= wordsByLength[pos + 1].size();

            for (int i = startingIndex; i < arr.length; i++) {
                buckets[arr[i].charAt(pos)].add(arr[i]);
            }

            index = startingIndex;
            for (ArrayList<String> thisBucket : buckets) {
                for (String s : thisBucket) {
                    arr[index++] = s;
                }

                thisBucket.clear();
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CIO720",
                "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
        System.out.println(Arrays.toString(arr));
        radixCountStrSort(arr, 7);
        System.out.println(Arrays.toString(arr));

        System.out.println("======================================================================================");

        String[] arr2 = {"1PGCI", "3IY", "3CIO", "4O", "1I", "4JZYE", "2NL", "2ATW"};
        System.out.println(Arrays.toString(arr2));
        changeStringSort(arr2, 5);
        System.out.println(Arrays.toString(arr2));
    }
}
