import java.util.*;

/**
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 *
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 */
public class KSimilarStrings {
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        int step = 0;
        Queue<AbstractMap.SimpleEntry<String, Integer>> queue = new ArrayDeque<>();
        Set<String> visit = new HashSet<>();
        queue.offer(new AbstractMap.SimpleEntry<>(s1, 0));
        visit.add(s1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                AbstractMap.SimpleEntry<String, Integer> poll = queue.poll();
                String cur = poll.getKey();
                int left = poll.getValue();
                if (cur.equals(s2)) {
                    return step;
                }
                while (left < n && cur.charAt(left) == s2.charAt(left)) {
                    left++;
                }
                for (int j = left + 1; j < n; j++) {
                    if (cur.charAt(j) == s2.charAt(j)) {
                        continue;
                    }
                    if (cur.charAt(j) == s2.charAt(left)) {
                        char[] arr = cur.toCharArray();
                        char c = arr[i];
                        arr[i] = arr[j];
                        arr[j] = c;
                        String next = new String(arr);
                        if (!visit.contains(next)) {
                            visit.add(next);
                            queue.offer(new AbstractMap.SimpleEntry<>(next, left + 1));
                        }

                    }
                }
            }
        }

        return step;
    }

    public static void main(String[] args) {
        KSimilarStrings kSimilarStrings = new KSimilarStrings();
        //"bccaba"
        //"abacbc"
        System.out.println(kSimilarStrings.kSimilarity("bccaba", "abacbc"));
    }
}
