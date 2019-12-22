package other.bitmap;

import java.util.BitSet;

/**
 * @author: Hello World
 * @date: 2018/9/25 11:35
 */
public class Test {
    public static void main(String[] args) {
        /* 运行前内存 */
        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1 = System.currentTimeMillis();

        /**
         * 2000000000/8 = 250000000 Byte
         * 250000000/1024 = 244140.625 KB
         * 244140.625/1024 = 238.42 MB
         * 所以20亿个数占大约238MB内存
         */
        BitSet set = new BitSet(2000000000);
        for (int i = 0; i < 2000000000; i++) {
            /* 假设898989这个数不在20亿个数里面 */
            if (i != 898989) {
                set.set(i, true);
            }
        }

        /* 创建20亿个数后所占内存 */
        long afterMemory = Runtime.getRuntime().totalMemory();
        long end1 = System.currentTimeMillis();

        System.out.println("总共内存使用:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:" + (end1 - start1) + "毫秒");

        long start2 = System.currentTimeMillis();
        boolean isExit1 = set.get(898989);
        boolean isExit2 = set.get(900000);
        long end2 = System.currentTimeMillis();

        /* 输出在20亿个数中判断898989是否包含在里面 */
        System.out.println(isExit1);
        System.out.println("20个亿中" + (isExit1 ? "包含" : "不包含") + 898989);
        System.out.println("20个亿中" + (isExit2 ? "包含" : "不包含") + 900000);
        System.out.println("查询用时:" + (end2 - start2) + "毫秒");
    }
}
