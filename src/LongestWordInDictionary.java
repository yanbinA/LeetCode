import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * <h3>720. 词典中最长的单词</h3>
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 *
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description longest-word-in-dictionary
 * @date 2022-03-17 0:26
 * @verison V1.0.0
 */
public class LongestWordInDictionary {
    Map<String, Integer> searchMap = new HashMap<>();
    Boolean[] iw;
    public String longestWord(String[] words) {

        int length = words.length;
        iw = new Boolean[length];
        for (int i = 0; i < length; i++) {
            searchMap.put(words[i], i);
        }
        String longest = "";
        for (int i = 0; i < length; i++) {
            String word = words[i];
            if (iw[i] == null) {
                dfs(word, i);
                if (iw[i] && (longest.length() < word.length() || (longest.length() == word.length() && longest.compareTo(word) > 0))) {
                    longest = word;
                }
            }
        }
        return longest;
    }

    private boolean dfs(String word, int i) {
        if (word.length() == 1) {
            iw[i] = true;
            return true;
        }
        String prefix = word.substring(0, word.length() - 1);
        if (searchMap.containsKey(prefix)) {
            Integer index = searchMap.get(prefix);
            iw[i] = iw[index];
            if (iw[index] == null) {
                iw[i] = dfs(prefix, index);
            }
            return iw[i];
        } else {
            iw[i] = false;
            return false;
        }

    }

    public static void main(String[] args) {
        LongestWordInDictionary dictionary = new LongestWordInDictionary();
        System.out.println(dictionary.longestWord(new String[]{"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"}));
    }
}
