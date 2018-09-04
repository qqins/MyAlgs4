package swordoffer.chapter6;

import java.util.Arrays;

/**
 * @author: Hello World
 * @date: 2018/9/4 8:17
 * <p>
 * 面试题61：扑克牌的顺子
 * 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 */
public class Question61 {
    public boolean isContinuous(int[] numbers) {
        if (numbers.length < 5) {
            return false;
        }
        Arrays.sort(numbers);
        int count = 0;
        //统计癞子
        for (int num : numbers) {
            if (num == 0) {
                count++;
            }
        }
        for (int i = count; i < numbers.length - 1; i++) {
            //顺子直接false
            if (numbers[i + 1] == numbers[i]) {
                return false;
            }
            //用癞子填补中间的空缺
            count -= numbers[i + 1] - numbers[i] - 1;
        }
        return count >= 0;
    }
}
