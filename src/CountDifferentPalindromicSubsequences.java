/**
 * 730. 统计不同回文子序列
 * 给定一个字符串 s，返回 s 中不同的非空「回文子序列」个数 。
 *
 * 通过从 s 中删除 0 个或多个字符来获得子序列。
 *
 * 如果一个字符序列与它反转后的字符序列一致，那么它是「回文字符序列」。
 *
 * 如果有某个 i , 满足 ai != bi ，则两个序列 a1, a2, ... 和 b1, b2, ... 不同。
 *
 * 注意：
 *
 * 结果可能很大，你需要对 109 + 7 取模 。
 */
public class CountDifferentPalindromicSubsequences {
    public int countPalindromicSubsequences(String s) {
        final int MOD = 1000000007;
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (chars[j] == chars[i]) {
                    int low = i + 1, high = j - 1;
                    while (low <= high && chars[low] != chars[j]) {
                        low++;
                    }
                    while (low <= high && chars[high] != chars[j]) {
                        high--;
                    }
                    if (low > high) {
                        dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % MOD;
                    } else if (low == high) {
                        dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % MOD;
                    } else {
                        dp[i][j] = (dp[i + 1][j - 1] * 2 % MOD - dp[low + 1][high - 1] + MOD) % MOD;
                    }
                } else {
                    dp[i][j] = ((dp[i + 1][j] + dp[i][j - 1]) % MOD - dp[i + 1][j - 1] + MOD) % MOD;
                }
            }
        }
        return dp[0][n - 1];
    }

}
