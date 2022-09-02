import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1417. 重新格式化字符串
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 *
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 *
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 */
public class ReformatTheString {
    public String reformat(String s) {
        int n = s.length();
        char[] chars = new char[n];
        int ll = 0, dl = 0;
        char[] array = s.toCharArray();
        for (char c : array) {
            if (Character.isDigit(c)) {
                chars[n - ++dl] = c;
            } else {
                chars[ll++] = c;
            }
        }
        if (Math.abs(dl - ll) > 1) {
            return "";
        }
        if (dl == ll) {
            for (int i = 0; i < ll; i++) {
                array[i*2 + 1] = chars[i];
                array[i*2] = chars[n - i - 1];
            }
        }
        if (dl > ll) {
            for (int i = 0; i < ll; i++) {
                array[i*2 + 1] = chars[i];
                array[i*2] = chars[n - i - 1];
            }
            array[n - 1] = chars[n - ll - 1];
        }
        if (dl < ll) {
            for (int i = 0; i < dl; i++) {
                array[i*2] = chars[i];
                array[i*2 + 1] = chars[n - i - 1];
            }
            array[n - 1] = chars[ll - 1];
        }
        return String.valueOf(array);

    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int key = groupSizes[i];
            List<Integer> value = null;
            if (map.containsKey(key)) {
                value = map.get(key);
            } else {
                value = new ArrayList<>();
                map.put(key, value);
            }
            value.add(i);

            if (value.size() == key) {
                list.add(value);
                map.put(key, new ArrayList<>());
            }
        }
        return list;
    }
}
