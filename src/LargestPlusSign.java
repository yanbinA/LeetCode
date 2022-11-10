/**
 * 764. 最大加号标志
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 *
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 *
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 */
public class LargestPlusSign {
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int[][] grid;
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] grid = new int[n][n];
        int[][] up = new int[n][n];
        int[][] down = new int[n][n];
        int[][] left = new int[n][n];
        int[][] right = new int[n][n];
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = -1;
        }
        for (int i = 0; i < n; i++) {
            int wc = 0;
            int lc = 0;
            for (int j = 0; j < n; j++) {
                wc = grid[i][j] == 0 ? wc + 1 : 0;
                left[i][j] = wc;
                lc = grid[j][i] == 0 ? lc + 1 : 0;
                down[j][i] = lc;
            }
            wc = 0;
            lc = 0;
            for (int j = n - 1; j >= 0; j--) {
                wc = grid[i][j] == 0 ? wc + 1 : 0;
                right[i][j] = wc;
                lc = grid[j][i] == 0 ? lc + 1 : 0;
                up[j][i] = lc;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, Math.min(Math.min(up[i][j], down[i][j]), Math.min(left[i][j], right[i][j])));
            }
        }
        return ans;
    }

    private int search(int i, int j, int[] dir, int min) {
        if (min <= 0 || grid[i][j] == -1) {
            return 0;
        }
        return search(i + dir[0], j + dir[1], dir, min - 1) + 1;
    }

    public static void main(String[] args) {
        LargestPlusSign sign = new LargestPlusSign();
        System.out.println(sign.orderOfLargestPlusSign(3, new int[][]{{0,1}}));
    }
}
