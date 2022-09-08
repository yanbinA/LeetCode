import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1592. 重新排列单词间的空格
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
 *
 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 *
 * 返回 重新排列空格后的字符串 。
 */
public class RearrangeSpacesBetweenWords {
    public String reorderSpaces(String text) {
        char[] chars = text.toCharArray();
        int n = text.length();
        int s = 0;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == ' ') {
                s++;
                if (!sb.isEmpty()) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
                continue;
            }
            sb.append(c);
        }
        if (!sb.isEmpty()) {
            list.add(sb.toString());
            sb = new StringBuilder();
        }
        int size = list.size() - 1;
        String str = size == 0 ? "" : " ".repeat(Math.max(0, s / size));
        String more = " ".repeat(Math.max(0, size == 0 ? s : s % size));
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i)).append(str);
        }
        sb.append(list.get(size)).append(more);
        return sb.toString();
    }
}
