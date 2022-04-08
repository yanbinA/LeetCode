import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N 叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 *
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */
public class NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(Node node, int deep, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        if (deep == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(deep).add(node.val);
        for (Node child : node.children) {
            dfs(child, deep + 1, ans);
        }
    }

    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                Node poll = queue.poll();
                List<Node> children = poll.children;
                if (children != null && children.size() != 0) {
                    queue.addAll(children);
                }
                list.add(poll.val);
            }
            ans.add(list);
        }
        return ans;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
