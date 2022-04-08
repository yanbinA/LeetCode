import java.util.*;

/**
 * 310. 最小高度树
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 *
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 *
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 *
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 *
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int x = findLongest(0, parent, adj);
        int y = findLongest(x, parent, adj);
        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int size = path.size();
        ans.add(path.get((size - 1)/ 2));
        if (size % 2 == 0) {
            ans.add(path.get(size / 2));
        }
        return ans;
    }

    private int findLongest(int curr, int[] parent, List<Integer>[] adj) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(curr);
        boolean[] visit = new boolean[parent.length];
        visit[curr] = true;
        int node = curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            node = curr;
            for (int e : adj[curr]) {
                if (!visit[e]) {
                    visit[e] = true;
                    parent[e] = curr;
                    stack.add(e);
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
        MinimumHeightTrees heightTrees = new MinimumHeightTrees();
        System.out.println(heightTrees.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
    }


}
