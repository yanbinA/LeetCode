import java.util.ArrayList;
import java.util.List;

/**
 * 589. N 叉树的前序遍历
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 */
public class AryTreePreorderTraversal {
    private List<Integer> ans;
    public List<Integer> preorder(Node root) {
        ans = new ArrayList<>();
        df(root);
        return ans;
    }

    private void df(Node root) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        if (root.children == null) {
            return;
        }
        for (Node child : root.children) {
            df(child);
        }
    }


    // Definition for a Node.
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
