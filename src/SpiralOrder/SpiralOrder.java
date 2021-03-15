package SpiralOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        dfs(matrix, list, 0, 0, matrix.length, matrix[0].length);
        return list;
    }

    private void dfs(int[][] matrix, List<Integer> list, int lx, int ly, int rx, int ry) {
        if (lx >= rx || ly >= ry) {
            return;
        }
        for (int i = ly; i < ry; i++) {
            list.add(matrix[lx][i]);
        }
        for (int i = lx + 1; i < rx; i++) {
            list.add(matrix[i][ry - 1]);
        }
        for (int i = ry - 2;i >= ly && rx - 1 > lx; i--) {
            list.add(matrix[rx - 1][i]);
        }
        for (int i = rx - 2; i > lx && ry - 1 > ly; i--) {
            list.add(matrix[i][ly]);
        }
        dfs(matrix, list, lx + 1, ly + 1, rx - 1, ry - 1);
    }
}
