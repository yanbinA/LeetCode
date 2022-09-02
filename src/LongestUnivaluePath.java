import common.TreeNode;

/**
 * 687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 */
public class LongestUnivaluePath {
    int max;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (root.left != null && root.val != root.left.val) {
            left = 0;
        }
        if (root.right != null && root.val != root.right.val) {
            right = 0;
        }
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        LongestUnivaluePath longestUnivaluePath = new LongestUnivaluePath();
        System.out.println(longestUnivaluePath.longestUnivaluePath(new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(1)), new TreeNode(5, null, new TreeNode(5)))));
    }
}
