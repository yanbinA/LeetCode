import common.TreeNode;

import java.util.*;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 */
public class FindDuplicateSubtrees {
    Map<Integer, Boolean> map;
    List<TreeNode> list;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        list = new ArrayList<>();
        dfs(root);
        return list;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 53 * (dfs(root.left) + 131);
        int right = 83 * (dfs(root.right)  + 131);
        int key = (root.val + left + right) ^ (left * right);
        if (map.containsKey(key)) {
            if (map.get(key)) {
                list.add(root);
            }
            map.put(key, false);
        } else {
            map.put(key, true);
        }
        return key;
    }
}
