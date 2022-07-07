import java.util.Arrays;

/**
 *324. 摆动排序 II
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 */
public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] ans = new int[n];
        int left = (n - 1) / 2, right = (n + 1) / 2;
        for (int i = 0; i <= left; i++) {
            ans[i * 2] = nums[left - i];
        }
        for (int i = 0; i < (n - right); i++) {
            ans[i*2 + 1] = nums[n - 1 - i];
        }
        System.arraycopy(ans, 0, nums, 0, n);
    }
    //1 2 3 4 5 6 7
    //4 7 3 6 2 5 1
    //1 2 3 4 5 6
    //3 6 2 5 1 4
}
