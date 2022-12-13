import java.util.*;

/**
 * 2503. 矩阵查询可获得的最大分数
 * 给你一个大小为 m x n 的整数矩阵 grid 和一个大小为 k 的数组 queries 。
 *
 * 找出一个大小为 k 的数组 answer ，且满足对于每个整数 queres[i] ，你从矩阵 左上角 单元格开始，重复以下过程：
 *
 * 如果 queries[i] 严格 大于你当前所处位置单元格，如果该单元格是第一次访问，则获得 1 分，并且你可以移动到所有 4 个方向（上、下、左、右）上任一 相邻 单元格。
 * 否则，你不能获得任何分，并且结束这一过程。
 * 在过程结束后，answer[i] 是你可以获得的最大分数。注意，对于每个查询，你可以访问同一个单元格 多次 。
 *
 * 返回结果数组 answer 。
 */
public class MaxPoints {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    //数组sort是queries由小到大排序的结果
    //广度优先搜索
    //vis[i][j]表示是否已访问过,也可以将grid[i][j]设置-1代表已访问过
    //stops记录查询过程中终止的点
    //1.广度优先搜索从(grid[0][0] + stops)查询sort[left](left=0)
    //2.如果grid[i][j] < sort[left], 分数+1,标记已访问,添加子节点
    //3.如果grid[i][j] >= sort[left],将点[i,j]放入stops
    //4.sort[left]查询全部终止, 记录left的分数. 将left++,继续以stops为开始节点查询
    public int[] maxPoints(int[][] grid, int[] queries) {
        int size = queries.length;
        int m = grid.length;
        int n = grid[0].length;
        int[] sort = queries.clone();
        boolean[][] vis = new boolean[m][n];
        Arrays.sort(sort);
        Queue<int[]> stops = new PriorityQueue<>(Comparator.comparing(item -> grid[item[0]][item[1]]));
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int left = 0;
        int score = 0;
        Map<Integer, Integer> scores = new HashMap<>();
        while (!queue.isEmpty() && left < size) {
            int val = sort[left];
            Queue<int[]> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                int i = poll[0];
                int j = poll[1];
                if (vis[i][j]) {
                    continue;
                }
                if (val <= grid[i][j]) {
                    stops.offer(poll);
                } else {
                    vis[i][j] = true;
                    score++;
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                            temp.offer(new int[]{x, y});
                        }
                    }
                }
            }
            //sort[left]查询全部终止, 记录left的分数. 将left++,继续以stops为开始节点查询
            if (temp.isEmpty()) {
                scores.put(val, score);
                left++;
                while (left < size && sort[left] == val) {
                    left++;
                }
                if (left >= size) {
                    break;
                }
                while (!stops.isEmpty()) {
                    int[] peek = stops.peek();
                    if (grid[peek[0]][peek[1]] < sort[left]) {
                        queue.offer(stops.poll());
                    } else {
                        queue.offer(stops.poll());
                        break;
                    }
                }
            } else {
                queue = temp;
            }
        }
        for (int i = 0; i < size; i++) {
            sort[i] = scores.getOrDefault(queries[i], score);
        }
        return sort;
    }

    public static void main(String[] args) {
        MaxPoints maxPoints = new MaxPoints();
        System.out.println(Arrays.toString(maxPoints.maxPoints(new int[][]{{9, 2, 13}, {12, 15, 7}, {3, 5, 1}}, new int[]{5, 8, 2, 10})));
    }

}
