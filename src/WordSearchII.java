import java.util.*;

/**
 * 单词搜索ii
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 */
public class WordSearchII {
    /**
     * 暴力遍历
     */
    private char[][] board;
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        Map<Character, List<String>> wordMap = new HashMap<>(64);
        for (String word : words) {
            char c = word.charAt(0);
            List<String> wordList = wordMap.getOrDefault(c, new ArrayList<>());
            wordList.add(word);
            wordMap.put(c, wordList);
        }
        Set<String> findWords = new HashSet<>();
        int m = this.board.length;
        int n = this.board[0].length;
        int[][] table = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = this.board[i][j];
                List<String> list = wordMap.get(c);
                if (list != null) {
                    for (String s : list) {
                        if (containWord(s, 0, table, i, j)) {
                            findWords.add(s);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(findWords);
    }

    private boolean containWord(String s, int index, int[][] table, int i, int j) {
        if (index == s.length()) {
            return true;
        }
        if (i < 0 || i >= table.length || j < 0 || j >= table[0].length) {
            return false;
        }
        if (table[i][j] != 0 || s.charAt(index) != board[i][j]) {
            return false;
        } else {
            table[i][j] = 1;
            boolean containWord = containWord(s, index + 1, table, i + 1, j)
                    || containWord(s, index + 1, table, i - 1, j)
                    || containWord(s, index + 1, table, i, j + 1)
                    || containWord(s, index + 1, table, i, j - 1);
            table[i][j] = 0;
            return containWord;

        }
    }

    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();
        char[][] broad = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(wordSearchII.findWords(broad, words));
    }
}
