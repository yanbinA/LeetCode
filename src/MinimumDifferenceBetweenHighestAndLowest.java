import java.util.Arrays;

/**
 * <h3>学生分数的最小差值</h3>
 * 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 *
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 *
 * 返回可能的 最小差值 。
 */
public class MinimumDifferenceBetweenHighestAndLowest {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) {
            return 0;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            min = Math.min(nums[i + k - 1] - nums[i], min);
        }

        return min;
    }
}
