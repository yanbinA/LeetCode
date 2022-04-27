import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 *
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 */
public class RandomPickIndex {
    static class Solution {
        private final int[] nums;
        private final Random random = new Random();
        private final Map<Integer, int[]> map = new HashMap<>();
        public Solution(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    map.get(nums[i])[0]++;
                    map.get(nums[i])[2] = i;
                } else {
                    map.put(nums[i], new int[]{1, i, i});
                }
            }
            this.nums = nums;
        }

        public int pick(int target) {
            int[] idx = map.get(target);
            int size = idx[0];
            if (size == 1) {
                return idx[1];
            }
            if (size == 2) {
                return random.nextInt(size) == 1 ? idx[1] : idx[2];
            }
            int random = this.random.nextInt(size);
            for (int i = idx[1]; i <= idx[2]; i++) {
                if (nums[i] == target) {
                    random--;
                    if (random < 0) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }




//执行用时：61 ms, 在所有 Java 提交中击败了27.84%的用户
//内存消耗：49.6 MB, 在所有 Java 提交中击败了22.11%的用户
    static class Solution1 {
        private int[] nums;
        private Random random = new Random();
        public Solution1(int[] nums) {
            this.nums = nums;
        }

        public int pick(int target) {
            int size = 0, left = -1, right = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    size++;
                    if (left == -1) {
                        left = i;
                    } else {
                        right = i;
                    }
                }
            }
            if (size == 1) {
                return left;
            }
            if (size == 2) {
                return random.nextInt(size) == 1 ? left : right;
            }
            int idx = random.nextInt(size);
            for (int i = left; i <= right; i++) {
                if (nums[i] == target) {
                    idx--;
                    if (idx < 0) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }


}
