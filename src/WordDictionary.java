/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 */
public class WordDictionary {
    private final WordNode root;
    public WordDictionary() {
        root = new WordNode();
    }

    public void addWord(String word) {
        root.insert(word, 0);
    }

    public boolean search(String word) {
        return root.find(word, 0);
    }
}

class WordNode {
    private final WordNode[] children;
    public boolean hasWord;

    public WordNode() {
        children = new WordNode[27];
        hasWord = false;
    }

    public void insert(String word, int index) {
        if (index == word.length()) {
            this.hasWord = true;
            return;
        }

        int pos = word.charAt(index) - 'a';
        if (children[pos] == null) {
            children[pos] = new WordNode();
        }
        children[pos].insert(word, index + 1);
    }

    public boolean find(String word, int index) {
        if (index == word.length()) {
            return this.hasWord;
        }
        char c = word.charAt(index);
        if (c == '.') {
            boolean find = false;
            for (WordNode child : children) {
                if (child != null) {
                    find = find || child.find(word, index + 1);
                }
            }
            return find;
        } else {
            int pos = c - 'a';
            if (children[pos] == null) {
                return false;
            }
            return children[pos].find(word, index + 1);
        }
    }
}
