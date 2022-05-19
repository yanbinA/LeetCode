import java.util.Arrays;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 *
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 */
public class MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            ans += nums[right--] - nums[left++];
        }
        return ans;
    }
}
