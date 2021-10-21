import java.util.Arrays;

/**
 * 一个数对(a,b)的 "数对和" 等于a + b。最大数对和是一个数对数组中最大的数对和。
 *
 * 比方说，如果我们有数对(1,5)，(2,3)和(4,4)，最大数对和为max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8。
 * 给你一个长度为 偶数n的数组nums，请你将 nums中的元素分成 n / 2个数对，使得：
 *
 * nums中每个元素恰好在 一个"数对"中，且
 * 最大"数对和"的值最小。
 * 请你在最优数对划分的方案下，返回最小的 最大数对和。
 */
public class MinimizeMaximumPairSum {
    //a,b,c,d
    //(a+b),(c+d) max=c+d
    //(a,d),(b,c) max=?
    //(a,c),(b,d) max=?
    //a+c<=a+d, b+d>=b+c
    //a+c<=b+c, b+d>=a+d
    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0, length = nums.length;
        for (int i = 0; i < length / 2; i++) {
            max = Math.max(max, nums[i] + nums[length - i - 1]);
        }
        return max;
    }
}
