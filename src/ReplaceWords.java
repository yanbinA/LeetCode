import java.util.List;

/**
 * 648. 单词替换
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 你需要输出替换之后的句子。
 */
public class ReplaceWords {
    Node node = new Node();
    public String replaceWords(List<String> dictionary, String sentence) {
        for (String s : dictionary) {
            add(s);
        }
        StringBuilder sb = new StringBuilder();
        String[] s = sentence.split(" ");
        for (String str : s) {
            sb.append(" ").append(search(str));
        }
        sb.deleteCharAt(0);
        return sb.toString();
    }

    public void add(String str) {
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

    public String search(String str) {
        int n = str.length();
        int left = 0;
        Node current = node;
        while (left < n) {
            int idx = str.charAt(left) - 'a';
            Node node = current.nodes[idx];
            if (node == null) {
                return str;
            }
            if (node.end) {
                return str.substring(0, left + 1);
            }
            current = node;
            left++;
        }
        return str;
    }

    static class Node {
        private boolean end;
        private Node[] nodes = new Node[26];
    }
}
