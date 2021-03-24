package pattern_132;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[n - 1]);
        int maxS = Integer.MIN_VALUE;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < maxS) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                maxS = stack.pop();
            }
            if (nums[i] > maxS) {
                stack.push(nums[i]);
            }
        }
        return false;
    }

    public boolean find132pattern1(int[] nums) {
        List<Integer[]> list = new ArrayList<>();
        for (int i = 1; i < nums.length;) {
            if (nums[i] > nums[i - 1]) {
                int left = i - 1;
                int right = i;
                while (i < nums.length && nums[i] > nums[i - 1]) {
                    if (find(list, nums[i])) {
                        return true;
                    }
                    right = i;
                    i++;
                }
                this.addSection(list, nums[left], nums[right]);
            } else {
                if (find(list, nums[i++])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  list中的区间，和[left, right]没有交际
     *  list中的区间必然是下降的， 一旦出现一个高区间将会合并掉高区间以下的区间
     */
    public void addSection(List<Integer[]> list, int left, int right) {
        Iterator<Integer[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer[] section = iterator.next();
            if (section[0] < right) {
                left = Math.min(section[0], left);
                iterator.remove();
            }
        }
        list.add(new Integer[]{left, right});
    }

    public boolean find(List<Integer[]> list, int num) {
        for (Integer[] section : list) {
            if (section[0] < num && section[1] > num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find132pattern pattern = new Find132pattern();
        System.out.println(pattern.find132pattern(new int[]{40, 50, 25, 35, 15, 35, 20}));
    }
}
