package simple;

/**
 * 寻找峰值
 *峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 */
public class FindPeakElement {
    /**
     * 二分查找
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     */
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 线性查找
     */
    public static int findPeakElement1(int[] nums) {
        int length = nums.length;
        boolean up = true;
        for (int i = 0; i < length; i++) {
            if (up && i == length - 1) {
                return i;
            }
            if (i == length - 1) {
                break;
            }
            if (up) {
                if (nums[i + 1] < nums[i]) {
                    return i;
                } else if (nums[i + 1] == nums[i]){
                    up = false;
                }
            } else {
                if (nums[i + 1] > nums[i]) {
                    up = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }
}
