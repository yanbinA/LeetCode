import java.util.Arrays;

/**
 * 899. 有序队列
 * 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 *
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 */
public class OrderlyQueue {
    public String orderlyQueue(String s, int k) {
        if (s.length() == 1) {
            return s;
        }
        if (k == 1) {
            String ans = s;
            int n = s.length();
            for (int i = 1; i < n; i++) {
                s = s.substring(1).concat(s.substring(0, 1));
                if (s.compareTo(ans) < 0) {
                    ans = s;
                }
            }
            return ans;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
