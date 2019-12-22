package company.ali;

import java.util.Scanner;

/**
 * @author: Hello World
 * @date: 2018/8/16 10:56
 * <p>
 * 某物流派送员p，需要给a、b、c、d4个快递点派送包裹，请问派送员需要选择什么的路线，
 * 才能完成最短路程的派送。假设如图派送员的起点坐标(0,0)，派送路线只能沿着图中的
 * 方格边行驶，每个小格都是正方形，且边长为1，如p到d的距离就是4。随机输入n个派送点坐标，
 * 求输出最短派送路线值（从起点开始完成n个点派送并回到起始点的距离）。
 * 输入
 * 4
 * 2,2
 * 2,8
 * 4,4
 * 7,2
 * 输出30
 * 输入
 * 2,2
 * 2,8
 * 6,6
 * 输出28
 */
public class Main {
    static class Point {
        int x, y;
        boolean isVisited;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.isVisited = false;
        }

        private int getLength(Point point) {
            return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
        }
    }

    static int minpath = Integer.MAX_VALUE;

    final static Point START = new Point(0, 0);

    public static void caculate(Point start, Point[] points, int sum, int count) {

        for (int i = 0; i < points.length; i++) {
            if (points[i].isVisited == false) {
                points[i].isVisited = true;
                count++;
                sum += start.getLength(points[i]);
                if (count == points.length) {
                    sum += points[i].getLength(START);
                    if (sum < minpath) {
                        minpath = sum;
                    }
                }
                caculate(points[i], points, sum, count);
                points[i].isVisited = false;
                count--;
                sum -= start.getLength(points[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());
        Point[] points = new Point[num];
        for (int i = 0; i < num; i++) {
            String[] locations = scanner.nextLine().trim().split(",");
            points[i] = new Point(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]));
        }
        caculate(new Point(0, 0), points, 0, 0);
        System.out.println(minpath);
    }
}