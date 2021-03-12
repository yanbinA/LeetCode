package IsValidSerialization;

/**
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
 * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 */
public class IsValidSerialization {
    public boolean isValidSerialization(String preorder) {
        char[] chars = preorder.toCharArray();
        int n = chars.length;
        int right = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] == '#' || chars[i] == ',') {
                continue;
            }
            int numLen = 0;
            while (--i >= 0 && chars[i] != ',') {
                numLen++;
            }
            i++;
            if (right - i < 4 + numLen) {
                return false;
            }
            right -= 4 + numLen;
        }
        return right == 0;
    }
}
