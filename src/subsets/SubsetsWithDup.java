package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列
 *
 * @author Depp
 */
public class SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        dfs(nums, list, 0, lists);
        lists.add(list);
        return lists;
    }

    private void dfs(int[] nums, List<Integer> list, int left, List<List<Integer>> lists) {
        Integer preValue = null;
        for (; left < nums.length; left++) {
            int num = nums[left];
            if (preValue != null && num == preValue) {
                continue;
            }
            list.add(num);
            dfs(nums, list, left + 1, lists);
            preValue = num;
            lists.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
    }
}
