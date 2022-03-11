import java.util.Arrays;

/**
 * <h3>2049. 统计最高分的节点数目</h3>
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 *
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 *
 * 请你返回有 最高得分 节点的 数目 。
 */
public class CountNodesWithTheHighestScore {
    //[-1,2,0,2,0]
    //[2,0,2,0,0]

    //
    private int length;
    private int count;
    private long maxScore;
    public int countHighestScoreNodes(int[] parents) {
        length = parents.length;
        Node[] nodes = new Node[length];
        Node root = new Node();
        nodes[0] = root;
        for (int i = 1; i < length; i++) {
            int parent = parents[i];
            Node parentNode = nodes[parent];
            if (parentNode == null) {
                parentNode = new Node();
            }
            Node childNode = nodes[i];
            if (childNode == null) {
                childNode = new Node();
            }
            if (parentNode.left == null) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            nodes[parent] = parentNode;
            nodes[i] = childNode;
        }
        dfs(root);
        return count;
    }

    private int dfs(Node node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);
        int sum = left + right + 1;
        int parentSum = length - sum;
        long score = (long) (parentSum == 0 ? 1 : parentSum) * (left == 0 ? 1 : left) * (right == 0 ? 1 : right);
        if (score == maxScore) {
            count++;
        }
        if (score > maxScore) {
            count = 1;
            maxScore = score;
        }
        return sum;
    }

    static class Node {
        public Node left;
        public Node right;
    }
}
