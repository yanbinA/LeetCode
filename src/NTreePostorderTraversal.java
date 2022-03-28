import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 590. N 叉树的后序遍历
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class NTreePostorderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        for (Node child : root.children) {
            dfs(child, ans);
        }
        ans.add(root.val);
    }


    public List<Integer> postorder1(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            ans.add(0, curr.val);
            stack.addAll(curr.children);
        }

        return ans;
    }

    static class Node {
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
