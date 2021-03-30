package search_a_2d_matrix;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchMatrix {
    //二分搜索
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int lm = 0, ln = 0, rm = m - 1, rn = n - 1;
        while (lm < rm || (lm == rm && rn >= ln)) {
            int mn = ((rm - lm) * n + (rn - ln)) / 2 + ln;
            int mm = lm + mn / n;
            mn = mn % n;
            if (matrix[mm][mn] < target) {
                lm = (mn + 1) >= n ? mm + 1 : mm;
                ln = (mn + 1) % n;
            } else if (matrix[mm][mn] > target) {
                rm = (mn - 1) < 0 ? mm - 1 : mm;
                rn = (mn - 1) < 0 ? n + mn - 1 : mn - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] arr = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int[][] arr = {{1,1}};
        SearchMatrix matrix = new SearchMatrix();
        System.out.println(matrix.searchMatrix(arr, 2));
    }
}
