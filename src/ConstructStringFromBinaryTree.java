import common.TreeNode;

import java.util.Stack;

/**
 * <p>
 * construct-string-from-binary-tree
 * 606. 根据二叉树创建字符串
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 *
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description construct-string-from-binary-tree
 * @date 2022-03-19 9:36
 * @verison V1.0.0
 */
public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sbf(root, sb);
        return sb.toString();
    }

    private void sbf(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return ;
        }
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            return;
        }
        if (root.left == null) {
            sb.append("()");
        } else {
            sb.append("(");
            sbf(root.left, sb);
            sb.append(")");
        }
        if (root.right != null) {
            sb.append("(");
            sbf(root.right, sb);
            sb.append(")");
        }
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
    }

}
