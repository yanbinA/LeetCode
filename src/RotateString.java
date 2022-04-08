/**
 * 796. 旋转字符串
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 *
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 */
public class RotateString {
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        if (n != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            return true;
        }
        for (int i = 1; i < n; i++) {
            if (goal.startsWith(s.substring(i)) && goal.endsWith(s.substring(0, i))) {
                return true;
            }
        }
        return false;
    }
}
