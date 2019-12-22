import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/11 15:22
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split("\\s+");
        String[] first = in.nextLine().split("\\s+");
        String[] second = in.nextLine().split("\\s+");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int[] score = new int[n];
        int[] wake = new int[n];
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(first[i]);
            wake[i] = Integer.parseInt(second[i]);
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int temp = 0;
            count += score[i] * wake[i];
            if (wake[i] == 0) {
                for (int j = i; j < i + k; j++) {
                    if (j < n) {
                        temp += score[j];
                    }
                }
            }
            max = Math.max(count + temp, max);
        }
        System.out.println(max);
    }
}
