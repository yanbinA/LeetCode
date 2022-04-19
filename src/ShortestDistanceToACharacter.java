/**
 * 821. 字符的最短距离
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 *
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 *
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 */
public class ShortestDistanceToACharacter {

    /**
     *  1. 从左往右, 计算c右侧距离
     *  2. 从右往左, 计算c左侧距离
     */
    public int[] shortestToChar1(String s, char c) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] ans = new int[n];
        int ci = -n;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                ci = i;
            }
            ans[i] = i - ci;
        }
        ci = 2 * n;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == c) {
                ci = i;
            }
            ans[i] = Math.min(ci - i, ans[i]);
        }
        return ans;
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] ans = new int[n];
        int ci = -n-1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                for (int j = Math.max((ci + i) / 2 + 1, 0); j < i; j++) {
                    ans[j] = i - j;
                }
                ci = i;
            }
            ans[i] = i - ci;
        }
        return ans;
    }
}
