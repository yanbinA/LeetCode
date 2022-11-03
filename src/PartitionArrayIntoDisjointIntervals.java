/**
 * 915. 分割数组
 * 给定一个数组 nums ，将其划分为两个连续子数组 left 和 right， 使得：
 *
 * left 中的每个元素都小于或等于 right 中的每个元素。
 * left 和 right 都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回 left 的 长度 。
 *
 * 用例可以保证存在这样的划分方法。
 */
public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int left = 0;
        int leftMax = nums[0];
        int curMax = nums[0];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num < leftMax) {
                leftMax = curMax;
                left = i;
            } else {
                curMax = Math.max(curMax, num);
            }
        }
        return left + 1;
    }
}
