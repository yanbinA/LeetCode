import java.util.Arrays;

/**
 * 1769. 移动所有球到每个盒子所需的最小操作数
 * 有 n 个盒子。给你一个长度为 n 的二进制字符串 boxes ，其中 boxes[i] 的值为 '0' 表示第 i 个盒子是 空 的，而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
 *
 * 在一步操作中，你可以将 一个 小球从某个盒子移动到一个与之相邻的盒子中。第 i 个盒子和第 j 个盒子相邻需满足 abs(i - j) == 1 。注意，操作执行后，某些盒子中可能会存在不止一个小球。
 *
 * 返回一个长度为 n 的数组 answer ，其中 answer[i] 是将所有小球移动到第 i 个盒子所需的 最小 操作数。
 *
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        int count = 0;
        int move = 0;
        char[] chars = boxes.toCharArray();
        for (int i = 0; i < n; i++) {
            move += count;
            res[i] = move;
            if (chars[i] == '1') {
                count++;
            }
        }
        count = 0;
        move = 0;
        for (int i = n - 1; i >= 0; i--) {
            move += count;
            res[i] += move;
            if (chars[i] == '1') {
                count++;
            }
        }
        return res;
    }
    /*
    count[i]是boxes[0, i]中小球总数
    left[i]是将boxes[0, i-1]中小球移动到boxes[i]所需的最小操作数
    left[i]=left[i-1]+count[i-1]
    right[i]是将boxes[i+1, n-1]中小球移动到boxes[i]所需的最小操作数
    right[i]=right[i+1]+(count[n-1] - count[i])
    res[i] = left[i]+right[i];
     */
    public int[] minOperations1(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        int[][] dp = new int[n+2][3];
        char[] chars = boxes.toCharArray();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                dp[i+1][0] = dp[i][0] + 1;
            } else {
                dp[i+1][0] = dp[i][0];
            }
            dp[i+1][1] = dp[i][1] + dp[i][0];
            res[i] += dp[i+1][1];
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[i+1][2] = dp[i+2][2] + dp[n][0] - dp[i+1][0];
            res[i] += dp[i+1][2];
        }

        return res;
    }

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMoveAllBallsToEachBox eachBox = new MinimumNumberOfOperationsToMoveAllBallsToEachBox();
        System.out.println(Arrays.toString(eachBox.minOperations("110")));
        System.out.println(Arrays.toString(eachBox.minOperations("001011")));
    }
}
