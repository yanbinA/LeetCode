import java.util.Arrays;

/**
 * 面试题 01.09. 字符串轮转
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 */
public class StringRotationLcci {
    public boolean isFlipedString(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        for (int i = 0; i < n;) {
            int left = 0;
            while (left < n && chars1[left] == chars2[i % n]) {
                left++;
                i++;
            }
            if (left == n) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        boolean col=false, row=false;
        for(int i=0; i<matrix.length; ++i) {
            for(int j=0; j<matrix[0].length; ++j){
                if (matrix[i][j]==0){
                    matrix[0][j]=matrix[i][0]=0;
                    if(i==0) {
                        row = true;
                    }
                    if(j==0) {
                        col = true;
                    }
                }
            }

        }

        for(int i=1; i<matrix.length; ++i){
            if (matrix[i][0]==0) {
                for(int j=1; j<matrix[0].length; ++j ) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i=1; i<matrix[0].length; ++i){
            if (matrix[0][i]==0) {
                for(int j=1; j<matrix.length; ++j ) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (row) {
            Arrays.fill(matrix[0], 0);
        }
        if (col) {
            for(int i=0; i<matrix.length; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}
