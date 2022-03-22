import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class TwoSumInputBST {
    public boolean findTarget(TreeNode root, int k) {
        //中序
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            }
            if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}
