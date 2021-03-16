package SpiralOrder;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        // 0-向右 1- 向下 2-向左 3-向上
        int direction = 0, x = 0, y = 0;
        for (int i = 1; i < n * n + 1; i++) {
            matrix[x][y] = i;
            switch (direction) {
                case 0:
                    if (y + 1 < n && matrix[x][y + 1] == 0) {
                        y++;
                    } else {
                        direction = 1;
                        x++;
                    }
                    break;
                case 1:
                    if (x + 1 < n && matrix[x + 1][y] == 0) {
                        x++;
                    } else {
                        direction = 2;
                        y--;
                    }
                    break;
                case 2:
                    if (y -1 >= 0 && matrix[x][y - 1] == 0) {
                        y--;
                    } else {
                        direction = 3;
                        x--;
                    }
                    break;
                case 3:
                    if (x -1 >= 0 && matrix[x - 1][y] == 0) {
                        x--;
                    } else {
                        direction = 0;
                        y++;
                    }
                    break;
                default:
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        GenerateMatrix matrix = new GenerateMatrix();
        matrix.generateMatrix(3);
    }
}
