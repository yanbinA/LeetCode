package dp;

/**
 *<h3>42. 接雨水</h3>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int minHeight = -1;
        int trap = 0;
        while (left <= right) {
            if (height[left] < height[right]) {
                minHeight = Math.max(minHeight, height[left]);
                trap += minHeight - height[left];
                left++;
            } else {
                minHeight = Math.max(minHeight, height[right]);
                trap += minHeight - height[right];
                right--;
            }
        }
        return trap;
    }
}
