import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 745. 前缀和后缀搜索
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 *
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 */
public class PrefixAndSuffixSearch {
    static class WordFilter {
        Map<String, List<Integer>> preMap = new HashMap<>();
        Map<String, List<Integer>> suffMap = new HashMap<>();

        public WordFilter(String[] words) {

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                char[] chars = word.toCharArray();
                int n = chars.length;
                StringBuilder pref = new StringBuilder();
                StringBuilder suff = new StringBuilder();
                for (int idx = 0; idx < n; idx++) {
                    pref.append(chars[idx]);
                    suff.insert(0, chars[n - idx - 1]);
                    String pk = pref.toString();
                    String sk = suff.toString();
                    if (preMap.containsKey(pk)) {
                        preMap.get(pk).add(i);
                    } else {
                        int elem = i;
                        preMap.put(pk, new ArrayList<Integer>(){{add(elem);}});
                    }
                    if (suffMap.containsKey(sk)) {
                        suffMap.get(sk).add(i);
                    } else {
                        int elem = i;
                        suffMap.put(sk, new ArrayList<Integer>(){{add(elem);}});
                    }
                }
            }
        }

        public int f(String pref, String suff) {
            List<Integer> pL = preMap.get(pref);
            List<Integer> sL = suffMap.get(suff);
            if (pL == null || sL == null) {
                return -1;
            }
            int pr = pL.size() - 1, sr = sL.size() - 1;
            while (pr >= 0 && sr >= 0) {
                if (pL.get(pr) > sL.get(sr)) {
                    pr--;
                    continue;
                }
                if (pL.get(pr) < sL.get(sr)) {
                    sr--;
                    continue;
                }
                if (pL.get(pr).intValue() == sL.get(sr)) {
                    return sL.get(sr);
                }

            }
            return -1;
        }

        public static void main(String[] args) {
            WordFilter filter = new WordFilter(new String[]{"abbba", "abba"});
            System.out.println(filter.f("ab", "ba"));
        }
    }

}
