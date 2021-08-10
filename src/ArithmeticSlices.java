/**
 * <h>等差数列划分</h>
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 */
public class ArithmeticSlices {
    public static int numberOfArithmeticSlices1(int[] nums) {
        int left = 0, right = 0, count = 0;
        while (left < nums.length - 2) {
            right = left + 1;
            int diff = nums[right] - nums[left];
            for (; right < nums.length - 1; right++) {
                if (diff != nums[right + 1] - nums[right]) {
                    break;
                }
            }
            if (right - left >= 2) {
                count += (right - left) * (right - left - 1) / 2;
            }
            left = right;
        }
        return count;
    }
    public static int numberOfArithmeticSlices(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] + nums[i - 2] == 2 * nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int count = 0;
        for (int i : dp) {
            count += i;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
        System.out.println(numberOfArithmeticSlices(new int[]{1, 2, 3}));
    }
}
