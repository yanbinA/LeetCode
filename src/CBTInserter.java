import common.TreeNode;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 */
public class CBTInserter {
    TreeNode[] arr = new TreeNode[20_000];
    int left = 1;
    public CBTInserter(TreeNode root) {
        arr[0] = root;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            TreeNode node = arr[idx];
            if (node.left != null) {
                int l = idx * 2 + 1;
                arr[l] = node.left;
                stack.add(l);
            }
            if (node.right != null) {
                int r = idx * 2 + 2;
                arr[r] = node.right;
                stack.add(r);
            }
        }
    }

    public int insert(int val) {
        for (int i = left; i < arr.length; i++) {
            if (arr[i] == null) {
                TreeNode node = new TreeNode(val);
                arr[i] = node;
                TreeNode parent = arr[(i - 1) / 2];
                if (i % 2 == 0) {
                    parent.right = node;
                } else {
                    parent.left = node;
                }
                left = i + 1;
                return parent.val;
            }
        }
        return -1;
    }

    public TreeNode get_root() {
        return arr[0];
    }
}
