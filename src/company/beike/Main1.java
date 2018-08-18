package company.beike;

/**
 * @author: Hello World
 * @date: 2018/8/18 20:15
 *
 * 扑克牌
 *
 * 众所周知，一副扑克牌按大小分为13种牌，每种牌各4个花色，总共52张牌。
 * 规定13种牌按从小到大的顺序分别为A23456789TJQK，现在从一副牌中抽取20张，
 * 每轮选择下列规则中的一项出牌：
 *
 * ①单牌：任意一张牌，如Q；
 *
 * ②对子：两张大小相同的牌，如77；
 *
 * ③三带：三张大小相同的牌，附带至多一张任意牌，如333A；
 *
 * ④四带：四张大小相同的牌，附带至多两张任意牌，如KKKK58；
 *
 * ⑤顺子：至少五张大小连续的牌，如789TJQ。
 *
 * 那么，至少需要多少轮才能将20张牌出完？
 *
 * 输入
 * 输入长度为20的字符串，表示所抽取的20张牌。
 *
 * 输出
 * 输出将20张牌出完所需的最少轮数。
 *
 *
 * 样例输入
 * 8K67A65K27T59K346AK2
 * 样例输出
 * 4
 *
 * Hint
 * 样例解释
 * 4轮出牌顺序为：
 * A234567
 * 56789T
 * KKKKA2
 * 6
 */
public class Main1 {
}
