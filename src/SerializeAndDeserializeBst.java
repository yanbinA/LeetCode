import common.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 */
public class SerializeAndDeserializeBst {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append(root.val)
                .append(serialize(root.left))
                .append(serialize(root.right))
                .append("}");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    int start;
    public TreeNode deserialize(String data) {
        start = 0;
        return desTree(data);
    }

    private TreeNode desTree(String data) {
        TreeNode treeNode = null;
        if (data.charAt(++start) == '}') {
            start++;
            return null;
        }
        int val = 0;
        while (Character.isDigit(data.charAt(start))) {
            val = val * 10 + data.charAt(start++) - '0';
        }
        treeNode = new TreeNode(val);
        if (data.charAt(start) == '{') {
            treeNode.left = desTree(data);
        }
        if (data.charAt(start) == '{') {
            treeNode.right = desTree(data);
        }
        start++;
        return treeNode;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        SerializeAndDeserializeBst bst = new SerializeAndDeserializeBst();
        String serialize = bst.serialize(treeNode);
        System.out.println(serialize);
        TreeNode node = bst.deserialize(serialize);
        System.out.println(node);
    }
}
