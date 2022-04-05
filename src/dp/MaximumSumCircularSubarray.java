package dp;

/**
 * <h3>环形子数组的最大和</h3>
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 *
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 *
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int length = nums.length;
        int left = 0, right = length - 1, sum = 0, max = Integer.MIN_VALUE;
        while (left <= right) {
            int lnum = nums[left];
            int rnum = nums[right];
            if (lnum > rnum) {
                sum += lnum;
                left++;
            } else {
                sum += rnum;
                right--;
            }
            if (max < sum) {
                max = sum;
            }
        }
        sum = 0;
        for (int num : nums) {
            sum += num;
            if (max < sum) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;


    }
}
