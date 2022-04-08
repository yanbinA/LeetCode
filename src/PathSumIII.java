import com.sun.org.apache.xpath.internal.operations.String;
import common.TreeNode;

import java.util.*;


/**
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 */
public class PathSumIII {
    
    public void test() {
        TreeNode node = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11)));
        System.out.println(pathSum(node, 8));
    }
    //[10,5,-3,3,2,null,8,3,-2,null,1]
    //10
    //5             -3
    //3     2       null    8
    //3 -2  null  1
    //10,8,8
    //5,-2,8
    //(3,-7,8)
    //(3,-10,8)->0 (-2,-10,8)->0 (3,8)(-2,8)
    public int pathSum(TreeNode root, int targetSum) {
        return bfs(root, targetSum, targetSum);
    }

    private int bfs(TreeNode root, int targetSum, int originalSum) {
        int count = 0;
        if (root == null) {
            return count;
        }
        if (targetSum == root.val) {
            count++;
        }
        count += bfs(root.left, targetSum - root.val, originalSum);
        count += bfs(root.right, targetSum - root.val, originalSum);
        count += bfs(root.left, originalSum, originalSum);
        count += bfs(root.right, originalSum, originalSum);
        return count;
    }
}
