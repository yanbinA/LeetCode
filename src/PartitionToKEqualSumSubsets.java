import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if(sum % k != 0) {
            return false;
        }
        int[] buckets = new int[k];
        Arrays.sort(nums);
        // 降序排列
        for(int i = 0; i < nums.length / 2; i++){
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
        return dfs(nums, 0, buckets, sum / k);
    }

    public boolean dfs(int[] nums, int index, int[] buckets, int total){
        if(index == nums.length){
            // 如果能把所有的球都放进去，说明肯定能成功
            return true;
        }
        // 如果球没有都放进去，那么就放第index个球
        for(int i = 0; i < buckets.length; i++){
            if(i != 0 && buckets[i] == buckets[i - 1]) {
                continue;
            }
            if(buckets[i] + nums[index] > total) {
                continue;
            }

            buckets[i] += nums[index];
            if(dfs(nums, index + 1, buckets, total)) {
                return true;
            }
            buckets[i] -= nums[index];
        }
        return false;
    }

}
