package dp;

/**
 * <h3>96. 不同的二叉搜索树</h3>
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            int num = 0;
            for (int r = 0; r < i; r++) {
                num += nums[r] * nums[i - r - 1];
            }
            nums[i] = num;
        }
        return nums[n];
    }
}
