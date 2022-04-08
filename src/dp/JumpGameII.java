package dp;

/**
 * <h3>跳跃游戏II</h3>
 *给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;
        int right = 0, maxIndex = 0, steps = 0;
        for (int i = 0; i < n - 1; i++) {
            maxIndex = Math.max(maxIndex, i + nums[i]);
            if (i == right) {
                right = maxIndex;
                steps++;
            }
        }
        return steps;
    }
}
