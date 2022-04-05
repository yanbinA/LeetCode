/**
 * <h3>540. 有序数组中的单一元素</h3>
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */
public class SingleElement {
    public int singleNonDuplicate(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int left = 0, right = length;
        while (left <= right) {
            int mid = left + (right -left) / 2;
            if (mid == 0 && nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else if (mid == length - 1 && nums[mid] == nums[mid - 1]) {
                right = mid - 2;
            } else if (mid != 0 && nums[mid] == nums[mid - 1]) {
                if ((mid - 1) % 2 == 1) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else if (mid != length - 1 && nums[mid] == nums[mid + 1]) {
                if ((mid) % 2 == 1) {
                    right = mid - 1;
                } else {
                    left = mid + 2;
                }
            } else {
                break;
            }
        }
        return nums[left + (right -left) / 2];
    }
}
