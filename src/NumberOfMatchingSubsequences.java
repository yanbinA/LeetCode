import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 792. 匹配子序列的单词数
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 *
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 *
 * 例如， “ace” 是 “abcde” 的子序列。
 */
public class NumberOfMatchingSubsequences {
    int[][] tables;
    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        int n = s.length();
        tables = new int[26][n + 1];
        for (int i = 0; i < n; i++) {
            tables[s.charAt(i) - 'a'][++tables[s.charAt(i) - 'a'][0]] = i;
        }
        for (String word : words) {
            if (subseq(word)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean subseq(String word) {
        int n = word.length();
        int wl = 0;
        int idx = -1;
        while (wl < n) {
            char c = word.charAt(wl++);
            int[] table = tables[c - 'a'];
            int left = 1;
            int right = table[0];
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (table[mid] <= idx) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (left > table[0]) {
                return false;
            }
            idx = table[left];
        }
        return true;
    }

    public static void main(String[] args) {
        NumberOfMatchingSubsequences subsequences = new NumberOfMatchingSubsequences();
        System.out.println(subsequences.numMatchingSubseq("abcde", new String[]{"a","bb","acd","ace"}));
//        System.out.println(bs(new int[]{4, 1, 2, 3, 4}, 1));
//        System.out.println(bs(new int[]{4, 1, 2, 3, 4}, -1));
//        System.out.println(bs(new int[]{4, 1, 2, 3, 4}, 4));
//        System.out.println(bs(new int[]{4, 1, 2, 3, 4}, 3));
//        System.out.println(bs(new int[]{4, 1, 2, 3, 4}, 5));
    }

    public static int bs(int[] arr, int idx) {
        int left = 1;
        int right = arr[0];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= idx) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
