/**
 * 713. 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 */
public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int product = 1;
        int n = nums.length;
        int left = 0, right = 0;
        int ans = 0;
        while (left < n) {
            if (right < n) {
                product *= nums[right++];
            } else {
                ans += (right - left + 1) * (right - left) / 2;
                break;
            }
            while (product >= k && left < n) {
                ans += right - left - 1;
                product /= nums[left++];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SubarrayProductLessThanK lessThanK = new SubarrayProductLessThanK();
        System.out.println(lessThanK.numSubarrayProductLessThanK(new int[]{10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19));
    }
}
