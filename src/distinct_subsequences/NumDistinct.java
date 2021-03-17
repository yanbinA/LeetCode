package distinct_subsequences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *  给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * numDistinct1 用到深度遍历+枝剪  --> 超时
 * 动态规划:
 * dp[i][j]表示t前i字符串在s前j字符串中的子序列数量
 * 当t[i]==s[j], dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1]
 * 当t[i]!=s[j], dp[i][j] = dp[i][j - 1]
 *
 */
public class NumDistinct {
    public static void main(String[] args) {
        NumDistinct distinct = new NumDistinct();
        System.out.println(distinct.numDistinct("", "rabbit"));
    }

    public int numDistinct(String s, String t) {
        int tLen = t.length();
        char[] targets = t.toCharArray();
        Set<Character> tSet = new HashSet<>(tLen);
        for (char aChar : targets) {
            tSet.add(aChar);
        }
        StringBuilder sb = new StringBuilder();
        int left = -1, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (tSet.contains(c)) {
                sb.append(c);
                if (c == targets[0] && left == -1) {
                    left = sb.length() - 1;
                }
                if (c == targets[tLen - 1]) {
                    right = sb.length();
                }
            }
        }
        if (left == -1) {
            return 0;
        }
        //实例二维数组, i + 1, j + 1 是要算上""字符
        int[][] dp = new int[tLen + 1][right - left + 1];
        //t的""字符串肯定是s的子序列, 所以dp[0]填充0
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < tLen + 1; i++) {
            char c = t.charAt(i - 1);
            for (int j = 1; j < right - left + 1; j++) {
                if (sb.charAt(j - 1 + left) == c) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[tLen][right - left];
    }

    public int numDistinct2(String s, String t) {
        //实例二维数组, i + 1, j + 1 是要算上""字符
        int tLen = t.length();
        int sLen = s.length();
        int[][] dp = new int[tLen + 1][sLen + 1];
        //t的""字符串肯定是s的子序列, 所以dp[0]填充0
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < tLen + 1; i++) {
            char c = t.charAt(i - 1);
            for (int j = 1; j < sLen + 1; j++) {
                if (s.charAt(j - 1) == c) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[tLen][sLen];
    }

    char[] targets;
    int count;
    public int numDistinct1(String s, String t) {
        int tLen = t.length();
        if (tLen == 0) {
            return 0;
        }
        if (s.length() < tLen) {
            return 0;
        }
        targets = t.toCharArray();
        Set<Character> tSet = new HashSet<>(tLen);
        for (char aChar : targets) {
            tSet.add(aChar);
        }
        StringBuilder sb = new StringBuilder();
        int left = -1, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (tSet.contains(s.charAt(i))) {
                sb.append(s.charAt(i));
                if (s.charAt(i) == targets[0] && left == -1) {
                    left = sb.length() - 1;
                }
                if (s.charAt(i) == targets[tLen - 1]) {
                    right = sb.length();
                }
            }
        }
        if (sb.length() < tLen) {
            return 0;
        }
        dfs(sb, left, right, 0);
        return count;
    }

    private void dfs(StringBuilder sb, int left, int right, int offset) {
        while (right - left >= targets.length - offset) {
            if (sb.charAt(left) == targets[offset]) {
                if (offset == targets.length - 1) {
                    count++;
                    left++;
                    continue;
                }
                dfs(sb, left + 1, right, offset + 1);
            }
            left++;
        }
    }
}
