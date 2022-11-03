/**
 * 940. 不同的子序列 II
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 *
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 *
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 */
public class DistinctSubsequencesII {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int[] arr = new int[26];
        int n = s.length();
        int sum = 0;
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            int tmp = arr[idx];
            arr[idx] = sum + 1;
            sum = ((sum + sum + 1) % MOD + (MOD - tmp)) % MOD;
        }
        return sum;
    }
}
