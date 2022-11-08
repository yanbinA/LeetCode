/**
 * 1684. 统计一致字符串的数目
 * 给你一个由不同字符组成的字符串 allowed 和一个字符串数组 words 。如果一个字符串的每一个字符都在 allowed 中，就称这个字符串是 一致字符串 。
 *
 * 请你返回 words 数组中 一致字符串 的数目。
 */
public class CountTheNumberOfConsistentStrings {
    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] tables = new boolean[26];
        for (char c : allowed.toCharArray()) {
            tables[c-'a'] = true;
        }
        int ans = 0;
        label:
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!tables[c-'a']) {
                    continue label;
                }
            }
            ans++;
        }
        return ans;
    }
}
