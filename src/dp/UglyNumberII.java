package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * <h3>丑数II</h3>
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *
 * 丑数 就是只包含质因数 2,3 和/或 5 的正整数。
 */
public class UglyNumberII {
    public static int nthUglyNumber(int n) {
        int[] uglies = new int[n];
        uglies[0] = 1;
        int idx2 = 0, idx3 = 0, idx5 = 0;
        for (int i = 1; i < n; i++) {
            int ugly2 = uglies[idx2] * 2,ugly3 = uglies[idx3] * 3, ugly5 = uglies[idx5] * 5;
            int min = Math.min(ugly2, Math.min(ugly3, ugly5));
            if (min == ugly2) {
                idx2++;
            }
            if (min == ugly3) {
                idx3++;
            }
            if (min == ugly5) {
                idx5++;
            }
            uglies[i] = min;
        }
        return uglies[n - 1];
    }

    public static int nthUglyNumber1(int n) {
        long ugly = 1;
        TreeMap<Long, Integer> treeMap = new TreeMap<>();
        treeMap.put(1L, 2);
        for (int i = 0; i < n; i++) {
            Map.Entry<Long, Integer> firstEntry = treeMap.firstEntry();
            ugly = firstEntry.getKey();
            int factor = firstEntry.getValue();
            treeMap.put(ugly * 2, factor);
            treeMap.put(ugly * 3, 3);
            treeMap.put(ugly * 5, 5);
            treeMap.remove(ugly);
        }
        return (int) ugly;
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
