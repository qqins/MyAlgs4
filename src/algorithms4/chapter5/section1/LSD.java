package algorithms4.chapter5.section1;

import java.io.*;

/**
 * @author: Hello World
 * @date: 2018/8/2 17:19
 * <p>
 * 低位优先的字符串排序
 * 字符串的单个字符从右到左扫描进行排序, 最终有序
 */
public class LSD {
    /**
     * 该方法适用于字符串长度都相等的情况
     *
     * @param a 字符数组
     * @param w 单个字符串的长度
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        //8位扩展的ASCII码
        int r = 256;
        String[] aux = new String[n];
        for (int i = w - 1; i >= 0; i--) {
            int[] count = new int[r + 1];
            //计算出现的频率
            for (int j = 0; j < n; j++) {
                count[a[j].charAt(i) + 1]++;
            }
            //将频率转化为索引
            for (int j = 0; j < r; j++) {
                count[j + 1] += count[j];
            }
            for (int j = 0; j < n; j++) {
                aux[count[a[j].charAt(i)]++] = a[j];
            }
            for (int j = 0; j < n; j++) {
                a[j] = aux[j];
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\_MyFile\\学习\\java\\Algorithms_4th\\algs4-data\\words3.txt");
        try (FileInputStream fis = new FileInputStream(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"))) {
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String[] str = sb.toString().split("\\p{javaWhitespace}+");
            int n = str.length;
            int w = str[0].length();
            sort(str, w);
            for (String s : str) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
