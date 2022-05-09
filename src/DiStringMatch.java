/**
 * 942. 增减字符串匹配
 * 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 *
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 */
public class DiStringMatch {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int l = 0, r = n;
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (c[i] == 'I') {
                ans[i] = l++;
            } else {
                ans[i] = r--;
            }
        }
        ans[n] = l;
        return ans;
    }
}
