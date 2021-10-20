package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <h3>删除并获得点数</h3>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 */
public class DeleteAndEarn {
    public static int deleteAndEarn(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int max = 0;
        for( int num : nums ) {
            max = Math.max(max, num);
        }
        int[] map = new int[max + 2];
        for (int num : nums) {
            map[num + 1] += num;
        }

        for (int i = 0; i < max; i++) {
            map[i + 2] = Math.max(map[i + 1], map[i] + map[i + 2]);
        }
        return map[map.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(deleteAndEarn(new int[]{3, 4, 2}));
    }
}
