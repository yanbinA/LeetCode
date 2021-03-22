package set_matrix_zeroes;

import java.util.Arrays;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法
 */
public class SetZeroes {
    public void setZeroes1(int[][] matrix) {
        int[] xZero = new int[matrix[0].length];
        int[] yZero = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    xZero[j] = 1;
                    yZero[i] = 1;
                }
            }
        }
        for (int i = 0; i < xZero.length; i++) {
            if (xZero[i] == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 0; i < yZero.length; i++) {
            if (yZero[i] == 1) {
                Arrays.fill(matrix[i], 0);
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        boolean rangeZero = false, rowZero = false;
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                rangeZero = true;
                break;
            }
        }
        int m = matrix[0].length;
        int n = matrix.length;
        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 0) {
                rowZero = true;
                break;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rangeZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowZero) {
            Arrays.fill(matrix[0], 0);
        }
    }
}
