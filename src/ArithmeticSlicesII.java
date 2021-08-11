import java.lang.annotation.Target;
import java.util.*;

/**
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 *
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 *
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 *
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
 */
public class ArithmeticSlicesII {
    int[] nums;
    Map<Long, Integer>[] stepMap;
    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        this.nums = nums;
        stepMap = new HashMap[length];
        int count = 0;
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                long step = nums[j] - nums[i];
                count += bfs(nums[j] + step, j, step);
            }
        }
        return count;
    }

    private int bfs(long target, int key, long step) {
        if (stepMap[key] != null && stepMap[key].containsKey(step)){
            return stepMap[key].get(step);
        } else {
            int count = 0;
            Map<Long, Integer> map = stepMap[key] == null ? new HashMap<>() : stepMap[key];
            for (int i = key + 1; i < nums.length; i++) {
                if (nums[i] == target) {
                    int bfs = bfs(target + step, i, step);
                    count += bfs + 1;
                }
            }
            map.put(step, count);
            stepMap[key] = map;
            return count;
        }
    }

    public static void main(String[] args) {
        ArithmeticSlicesII slicesII = new ArithmeticSlicesII();
        //[-2147483648,0,-2147483648]
        System.out.println(slicesII.numberOfArithmeticSlices(new int[]{7, 7, 7, 7, 7}));
        slicesII.nums = null;
        slicesII.stepMap = null;
        System.out.println(slicesII.numberOfArithmeticSlices(new int[]{-2147483648,0,-2147483648}));

    }
}
