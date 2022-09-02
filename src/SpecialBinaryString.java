
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *761. 特殊的二进制序列
 * 特殊的二进制序列是具有以下两个性质的二进制序列：
 *
 * 0 的数量与 1 的数量相等。
 * 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。
 * 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。)
 *
 * 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？
 */
public class SpecialBinaryString {
    public String makeLargestSpecial(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int c = 0;
        List<String> list = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                c++;
            } else {
                c--;
                if (c == 0) {
                    list.add("1".concat(makeLargestSpecial(s.substring(left + 1, i)).concat("0")));
                    left = i + 1;
                }
            }
        }
        list.sort(Comparator.reverseOrder());
        return String.join("", list);
    }
}
