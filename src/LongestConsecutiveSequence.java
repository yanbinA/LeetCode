import java.util.HashMap;
import java.util.Map;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class LongestConsecutiveSequence {
    Map<Integer, Integer> map = new HashMap<>();
    public int longestConsecutive(int[] nums) {
        for (int num : nums) {
            map.put(num, num);
        }
        for (int num : nums) {
            this.merge(num, num + 1);
        }
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, find(num) - num + 1);
        }
        return max;
    }

    public Integer find(int key) {
        if (!map.containsKey(key)) {
            return null;
        }
        int val = map.get(key);
        if (val == key) {
            return key;
        }
        int find = find(val);
        map.put(key, find);
        return find;
    }

    public void merge(Integer x, Integer y) {
        x = find(x);
        y = find(y);
        if (x == null || y == null) {
            return;
        }
        if (x == y.intValue()) {
            return;
        }
        map.put(x, y);
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence sequence = new LongestConsecutiveSequence();
        System.out.println(sequence.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }
}
