package search_a_2d_matrix;


/**
 * <h3>搜索二维矩阵II</h3>
 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

 每行的元素从左到右升序排列。
 每列的元素从上到下升序排列。
 */
public class SearchMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix[0].length - 1;
        for (int[] row : matrix) {
            if (row[0] > target) {
                return false;
            }
            int left = 0, right = r;
            while (left < right) {
                int middle = left + (right - left + 1) / 2;
                if (row[middle] == target) {
                    return true;
                }
                if (row[middle] > target) {
                    right = middle - 1;
                }
                if (row[middle] < target) {
                    left = middle;
                }
            }
            if (row[left] == target) {
                return true;
            }
            r = left;
        }
        return false;
    }
// {1,4,7,11,15}
// {2,5,8,12,19}
// {3,6,9,16,22}
// {10,13,14,17,24}
// {18,21,23,26,30}
    public static void main(String[] args) {
        //        int[][] arr = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int[][] arr = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(searchMatrix(arr, 5));
    }
}
