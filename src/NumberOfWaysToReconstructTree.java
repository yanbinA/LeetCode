import java.util.HashMap;
import java.util.Map;

/**
 * <h3>1719. 重构一棵树的方案数</h3>
 * 给你一个数组 pairs ，其中 pairs[i] = [xi, yi] ，并且满足：
 *
 * pairs 中没有重复元素
 * xi < yi
 * 令 ways 为满足下面条件的有根树的方案数：
 *
 * 树所包含的所有节点值都在 pairs 中。
 * 一个数对 [xi, yi] 出现在 pairs 中 当且仅当 xi 是 yi 的祖先或者 yi 是 xi 的祖先。
 * 注意：构造出来的树不一定是二叉树。
 * 两棵树被视为不同的方案当存在至少一个节点在两棵树中有不同的父节点。
 *
 * 请你返回：
 *
 * 如果 ways == 0 ，返回 0 。
 * 如果 ways == 1 ，返回 1 。
 * 如果 ways > 1 ，返回 2 。
 * 一棵 有根树 指的是只有一个根节点的树，所有边都是从根往外的方向。
 *
 * 我们称从根到一个节点路径上的任意一个节点（除去节点本身）都是该节点的 祖先 。根节点没有祖先。
 */
public class NumberOfWaysToReconstructTree {

    public int checkWays(int[][] pairs) {
        int ways = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] pair : pairs) {
            for (int i : pair) {
                int count = map.getOrDefault(i, 0);
                map.put(i, count + 1);
            }
        }
        int childSize = map.size() - 1;
        for (int value : map.values()) {
            if (value == childSize) {
                ways++;
            }
        }
        return Math.min(ways, 2);
    }

}
