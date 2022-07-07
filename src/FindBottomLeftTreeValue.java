import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 */
public class FindBottomLeftTreeValue {
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int ans = 0;
        while (!list.isEmpty()) {
            TreeNode node = list.poll();
            if (node.right != null) {
                list.offer(node.right);
            }
            if (node.left != null) {
                list.offer(node.left);
            }
            ans = node.val;
        }
        return ans;
    }
}
