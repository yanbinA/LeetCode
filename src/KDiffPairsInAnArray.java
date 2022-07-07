import java.util.Arrays;

/**
 * 532. 数组中的 k-diff 数对
 * 给定一个整数数组和一个整数 k，你需要在数组里找到 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 *
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 *
 * 0 <= i < j < nums.length
 * |nums[i] - nums[j]| == k
 * 注意，|val| 表示 val 的绝对值。
 */
public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = 0;
        int n = nums.length;
        int ans = 0;
        while (right < n) {
            if (left == right) {
                right++;
                continue;
            }
            int diff = nums[right] - nums[left];
            if (diff == k) {
                ans++;
                right++;
                while (right < n && nums[right] == nums[right - 1]) {
                    right++;
                }
            } else if (diff < k) {
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
