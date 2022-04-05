/**
 * <h3>747. 至少是其他数字两倍的最大数</h3>
 * 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 *
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 */
public class LargestNumberAtLeastTwiceOthers {
    public int dominantIndex(int[] nums) {
        int index = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > max) {
                if (num >= max * 2) {
                    index = i;
                } else {
                    index = -1;
                }
                max = num;
            } else {
                if (max < num * 2) {
                    index = -1;
                }
            }
        }
        return index;
    }
}
