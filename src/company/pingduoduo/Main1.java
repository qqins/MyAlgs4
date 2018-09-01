package company.pingduoduo;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/30 19:11
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int HP = scanner.nextInt();
        int normalAttack = scanner.nextInt();
        int buffedAttack = scanner.nextInt();
        if (normalAttack == 0 && buffedAttack == 0) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        if (normalAttack * 2 >= buffedAttack) {
            while (HP > 0) {
                HP -= normalAttack;
                count++;
            }
        } else {
            int flag = 1;
            while (HP > 0) {
                if (HP <= normalAttack) {
                    HP -= normalAttack;
                    count++;
                } else {
                    if (flag % 2 == 0) {
                        HP -= buffedAttack;
                        count++;
                    }
                    flag++;
                }
            }
        }
        System.out.println(count);
    }
}
