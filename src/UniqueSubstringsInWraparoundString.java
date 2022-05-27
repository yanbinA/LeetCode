/**
 * 467. 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 *
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." .
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。
 */
public class UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        char[] chars = p.toCharArray();
        int index = 0;
        int[] count = new int[26];
        for(int i = 0; i < chars.length; ++i) {
            if(i == 0 || (chars[i] - chars[i-1] != 1 && chars[i-1] - chars[i] != 25)) {
                index = i;
            }
            count[chars[i]-'a'] = Math.max(count[chars[i]-'a'], i - index + 1);
        }
        int ans = 0;
        for (int c : count) {
            ans += c;
        }
        return ans;
    }

    public static void main(String[] args) {
        UniqueSubstringsInWraparoundString wraparoundString = new UniqueSubstringsInWraparoundString();
        System.out.println(wraparoundString.findSubstringInWraproundString("zab"));
    }
}
