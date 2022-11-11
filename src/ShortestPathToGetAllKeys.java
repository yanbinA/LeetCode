import java.util.*;

/**
 * 864. 获取所有钥匙的最短路径
 * 给定一个二维网格 grid ，其中：
 *
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 *
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 *
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 */
public class ShortestPathToGetAllKeys {
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int k = 0, sx = 0, sy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                }
                if (Character.isLowerCase(grid[i].charAt(j))) {
                    k++;
                }
            }
        }
        int max = 1 << k;
        int[][][] step = new int[m][n][max];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(step[i][j], Integer.MAX_VALUE);
            }
        }
        step[sx][sy][0] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, 0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = poll[0] + dirs[i][0], y = poll[1] + dirs[i][1];
                if (x < 0 || x == m || y < 0 || y == n ) {
                    continue;
                }
                char c = grid[x].charAt(y);
                if (c == '#') {
                    continue;
                }
                if (c == '.' || c == '@') {
                    if (step[x][y][poll[2]] > poll[3] + 1) {
                        step[x][y][poll[2]] = poll[3] + 1;
                        queue.add(new int[]{x, y, poll[2], poll[3] + 1});
                    }
                }
                if (Character.isUpperCase(c)) {
                    if ((poll[2] & (1 << (c - 'A'))) == 0 || step[x][y][poll[2]] <= poll[3] + 1) {
                        continue;
                    }
                    step[x][y][poll[2]] = poll[3] + 1;
                    queue.add(new int[]{x, y, poll[2], poll[3] + 1});
                }
                if (Character.isLowerCase(c)) {
                    if (step[x][y][poll[2] | (1 << (c - 'a'))] <= poll[3] + 1) {
                        continue;
                    }
                    if ((max - 1) == (poll[2] | (1 << (c - 'a')))) {
                        return poll[3] + 1;
                    }
                    step[x][y][poll[2] | (1 << (c - 'a'))] = poll[3] + 1;
                    queue.add(new int[]{x, y, poll[2] | (1 << (c - 'a')), poll[3] + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathToGetAllKeys keys = new ShortestPathToGetAllKeys();
        System.out.println(keys.shortestPathAllKeys(new String[]{"@.a..", "###.#", "b.A.B"}));
    }


}
