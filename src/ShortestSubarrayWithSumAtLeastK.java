/**
 * 862. 和至少为 K 的最短子数组
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 */
public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }

        int[]dp = new int[n+1];
        int l = 0, r = 0, res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            while (l != r && preSum[dp[r - 1]] >= preSum[i]) {
                r--;
            }
            while (l != r && preSum[i] - preSum[dp[l]] >= k) {
                res = Math.min(res, i - dp[l]) ;
                l++;
            }
            dp[r++] = i;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
