package company.shenxinfu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/9/21 19:56
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
        try {
            Date preData = sdf.parse(str);
            Date nowData = sdf.parse("2018 8 1");
            long res = (nowData.getTime() - preData.getTime()) / (1000 * 3600 * 24);
            System.out.println(res);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
