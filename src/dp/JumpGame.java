package dp;

/**
 * <h3>跳跃游戏</h3>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int length = nums.length;
        int left = 0, right = 0;
        while (left <= right) {
            if ((right = Math.max(left + nums[left++], right)) >= length - 1) {
                break;
            }
        }
        return right >= length - 1;
    }
}
