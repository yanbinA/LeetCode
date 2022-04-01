import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *954. 二倍数对数组
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 */
public class ArrayOfDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int j : arr) {
            int count = map.getOrDefault(j, 0);
            map.put(j, ++count);
        }
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> firstEntry = map.firstEntry();
            int key = firstEntry.getKey();
            if (key < 0 && key % 2 != 0) {
                return false;
            }
            int pair = key << 1;
            if (key < 0) {
                pair = key >> 1;
            }
            if (!map.containsKey(pair)) {
                return false;
            }
            int pc = map.get(pair);
            if (pc > 0) {
                int count = firstEntry.getValue() - 1;
                if (count == 0) {
                    map.remove(key);
                } else {
                    map.put(key, count);
                }
                if (--pc == 0) {
                    map.remove(pair);
                } else {
                    map.put(pair, pc);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *执行结果：
     * 执行用时：44 ms, 在所有 Java 提交中击败了61.85%
     * 内存消耗：48.5 MB, 在所有 Java 提交中击败了42.78%
     */
    public boolean canReorderDoubled1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            int count = map.getOrDefault(j, 0);
            map.put(j, ++count);
        }
        Arrays.sort(arr);
        for (int i : arr) {
            int count = map.get(i);
            if (count > 0) {
                if (i < 0 && i % 2 != 0) {
                    return false;
                }
                int pair = i << 1;
                if (i < 0) {
                    pair = i >> 1;
                }
                if (!map.containsKey(pair)) {
                    return false;
                }
                int pc = map.get(pair);
                if (pc > 0) {
                    map.put(i, --count);
                    map.put(pair, --pc);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArrayOfDoubledPairs pairs = new ArrayOfDoubledPairs();
        System.out.println(pairs.canReorderDoubled(new int[]{3, 3, 1, 6}));
        System.out.println(pairs.canReorderDoubled(new int[]{-2, -4, 2, 4}));
    }
}
