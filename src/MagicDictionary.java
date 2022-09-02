/**
 * 676. 实现一个魔法字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 *
 * 实现 MagicDictionary 类：
 *
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 */
public class MagicDictionary {
    private Node node;

    public MagicDictionary() {
        node = new Node();
    }

    public void buildDict(String[] dictionary) {
        for (String str : dictionary) {
            char[] chars = str.toCharArray();
            Node current = node;
            for (char c : chars) {
                int idx = c - 'a';
                if (current.nodes[idx] == null) {
                    Node node = new Node();
                    current.nodes[idx] = node;
                }
                current = current.nodes[idx];
            }
            current.end = true;
        }
    }

    public boolean search(String searchWord) {
        int n = searchWord.length();
        int left = 0;
        Node current = node;
        while (left < n) {
            int idx = searchWord.charAt(left) - 'a';
            Node node = current.nodes[idx];
            if (node == null) {
                for (Node temp : current.nodes) {
                    if (temp == null) {
                        continue;
                    }
                    left++;
                    current = temp;
                    while (left < n) {
                        idx = searchWord.charAt(left) - 'a';
                        node = current.nodes[idx];
                        if (node == null) {
                            return false;
                        }
                        left++;
                        current = node;
                    }
                    if (current.end) {
                        return true;
                    }
                }
            }
            current = node;
            left++;
        }
        return false;
    }

    static class Node {
        private boolean end;
        private Node[] nodes = new Node[26];
    }

}
