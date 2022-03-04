import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h3>2104. 子数组范围和</h3>
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 *
 * 返回 nums 中 所有 子数组范围的 和 。
 *
 * 子数组是数组中一个连续 非空 的元素序列。
 */
public class SumOfSubarrayRanges {

    /**
     * 单调栈
     * 定义如果 nums[i]=nums[j]，那么 nums[i] 与 nums[j] 的逻辑大小由下标 i 与下标 j 的逻辑大小决定，
     * 即如果 i<j，那么 nums[i] 逻辑上小于 nums[j]
     * @param nums
     * @return
     */
    public long subArrayRanges(int[] nums) {
        int length = nums.length;
        int[] minLeft = new int[length];
        int[] minRight = new int[length];
        int[] maxLeft = new int[length];
        int[] maxRight = new int[length];
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            while (!minStack.isEmpty() &&  nums[minStack.peek()] > nums[i]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);
            while (!maxStack.isEmpty() &&  nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = length - 1; i >= 0; i--) {
            while (!minStack.isEmpty() &&  nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? length : minStack.peek();
            minStack.push(i);
            while (!maxStack.isEmpty() &&  nums[maxStack.peek()] < nums[i]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? length : maxStack.peek();
            maxStack.push(i);
        }
        long maxSum = 0, minSum = 0;
        for (int i = 0; i < length; i++) {
            minSum += (long) (i - minLeft[i]) * (minRight[i] - i) * nums[i];
            maxSum += (long) (i - maxLeft[i]) * (maxRight[i] - i) * nums[i];
        }
        return maxSum - minSum;
    }

    public long subArrayRanges1(int[] nums) {
        int length = nums.length;
        long sum = 0;
        for (int i = 0; i < length; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i + 1; j < length; j++) {
                int num = nums[j];
                if (num > max) {
                    max = num;
                }
                if (min > num) {
                    min = num;
                }
                sum += max - min;
            }
        }
        return sum;
    }
}
