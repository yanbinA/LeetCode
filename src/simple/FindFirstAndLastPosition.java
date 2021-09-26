package simple;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <h3>在排序数组中查找元素的第一个和最后一个位置</h3>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
public class FindFirstAndLastPosition {
    @Test
    public void test() {
        System.out.println(Arrays.toString(searchRange(new int[]{1, 1, 1}, 1)));
        System.out.println(Arrays.toString(searchRange(new int[]{1, 2, 3}, 1)));
        System.out.println(Arrays.toString(searchRange(new int[]{0, 2, 3}, 1)));
    }


    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int[] range = {-1, -1};
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] == target) {
                range[0] = leftRange(nums, target, left, mid);
                range[1] = rightRange(nums, target, mid, right);
                return range;
            }
        }
        return range;
    }

    private int rightRange(int[] nums, int target, int left, int right) {
        int rightRange = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] == target) {
                rightRange = mid;
                left = mid + 1;
            }
        }
        return rightRange;
    }

    private int leftRange(int[] nums, int target, int left, int right) {
        int leftRange = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] == target) {
                leftRange = mid;
                right = mid - 1;
            }
        }
        return leftRange;
    }
}
