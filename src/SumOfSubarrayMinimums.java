import java.util.Arrays;

/**
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */
public class SumOfSubarrayMinimums {
    /*
    当index=i时, 找到arr[j] <= arr[i]的临界点idx
    所有arr[i]为最后一个元素的子数组中最小值的和为sums[i] = sums[idx] + (i - idx) * arr[i]
    在寻找idx的过程中,[idx + 1, i]中的元素都>=arr[i],所以在下一次寻找过程可以忽略这一区间
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;
        int n = arr.length;
        int[] dp = new int[n];
        int[] stack = new int[n];
        int right = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i];
            int j = -1;
            while (right > 0) {
                if (arr[stack[right - 1]] < arr[i]) {
                    j = stack[right - 1];
                    break;
                }
                right--;
            }
            stack[right++] = i;
            dp[i] = (j == -1 ? 0 : dp[j]) + (i - j) * arr[i];
            ans = (ans + dp[i]) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        SumOfSubarrayMinimums sum = new SumOfSubarrayMinimums();
        System.out.println(sum.sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }
}
