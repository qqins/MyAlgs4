package leetcode.page14;

import java.util.*;

/**
 * @author: Hello World
 * @date: 2018/8/11 14:11
 * <p>
 * 841. 钥匙和房间
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，
 * 并且房间里可能有一些钥匙能使你进入下一个房间。
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j]
 * 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。
 * 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 * 你可以自由地在房间之间来回走动。
 * 如果能进入每个房间返回 true，否则返回 false。
 * <p>
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * <p>
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 */
public class Exercise841 {
    /**
     * BFS
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int room = queue.poll();
            //若该房间访问过,则跳过
            if (visited.contains(room)) {
                continue;
            }
            //将房间所有钥匙加入queue,只要queue中有的,visited都可以访问
            queue.addAll(rooms.get(room));
            //可以访问的房间
            visited.add(room);
        }
        //若访问过房间的数目与房间总数相等,则返回true
        return visited.size() == rooms.size();
    }

    /**
     * DFS
     */
    public boolean canVisitAllRoomsByDfs(List<List<Integer>> rooms) {
        if (rooms.size() == 0) {
            return true;
        }
        boolean[] visited = new boolean[rooms.size()];
        visit(rooms, visited, 0);
        for (boolean flag : visited) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    private void visit(List<List<Integer>> rooms, boolean[] visited, int room) {
        if (!visited[room]) {
            visited[room] = true;
            for (Integer nextRoom : rooms.get(room)) {
                visit(rooms, visited, nextRoom);
            }
        }
    }
}
