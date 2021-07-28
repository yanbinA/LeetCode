import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 */
public class DistanceInBinaryTree {
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        dfs(root, target, linkedList);
        int distance = 0;
        TreeNode node = null;
        while (!linkedList.isEmpty() && distance <= k) {
            TreeNode last = linkedList.pollLast();
            if (last.left != node) {
                dps(last.left, k - distance);
            }
            node = last;
        }
    }

    private static boolean dfs(TreeNode root, TreeNode target, LinkedList<TreeNode> list) {
        if (root == null) {
            return false;
        }
        list.add(root);
        if (root.val == target.val) {
            return true;
        }
        if (dfs(root.left, target, list) || dfs(root.right, target, list)) {
            return true;
        }
        list.pollLast();
        return false;
    }
}


