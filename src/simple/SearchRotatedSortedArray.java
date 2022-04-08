package simple;



/**
 * <h3>搜索旋转排序数组</h3>
 *整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchRotatedSortedArray {

    
    public void test() {
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 6));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 9));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 1));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 2));
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 3));
    }

    public int search(int[] nums, int target) {

        int key = nums[0];
        if (key == target) {
            return 0;
        }
        if (key < target) {
            int left = 1, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midVal = nums[mid];
                if (midVal == target) {
                    return mid;
                }
                if (midVal > target) {
                    right = mid - 1;
                }
                if (midVal < target) {
                    if (midVal < key) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        } else {
            int left = 1, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midVal = nums[mid];
                if (midVal == target) {
                    return mid;
                }
                if (midVal > key) {
                    left = mid + 1;
                }
                if (midVal < key) {
                    if (midVal < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}
