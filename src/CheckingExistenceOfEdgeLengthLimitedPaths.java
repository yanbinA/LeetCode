import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 1697. 检查边长度限制的路径是否存在
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
 *
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 *
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {
    //ans[k] = [pk, ak, max]表示点pk到ak最优路径, max表示路径上的最大边
    //假设pk到ak,依次经过其他点[ki,..,kj], 那么[pk,ki] [pk,kj]的最优路径也已经计算过了
    Map<Integer, Integer>[] grid;
    Map<Integer, Map<Integer, Integer>> standard;
    int[] vis;
    public boolean[] distanceLimitedPathsExist1(int n, int[][] edgeList, int[][] queries) {
        boolean[] answer = new boolean[queries.length];
        standard = new HashMap<>();
        grid = new HashMap[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new HashMap<>();
        }
        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            int val = grid[u].getOrDefault(v, Integer.MAX_VALUE);
            val = Math.min(val, edge[2]);
            grid[u].put(v, val);
            grid[v].put(u, val);
        }
        for (int i = 0; i < queries.length; i++) {
            answer[i] = queries[i][2] > bfs(queries[i][0], queries[i][1]);
        }
        return answer;
    }

    private int bfs(int p, int q) {
        if (p > q) {
            int temp = p;
            p = q;
            q = temp;
        }
        if (standard.containsKey(p) && standard.get(p).containsKey(q)) {
            return standard.get(p).get(q);
        }
        return 0;
    }

    //暴力搜索
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        boolean[] answer = new boolean[queries.length];
        grid = new HashMap[n];
        vis = new int[n];
        for (int i = 0; i < n; i++) {
            grid[i] = new HashMap<>();
        }
        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            int val = grid[u].getOrDefault(v, Integer.MAX_VALUE);
            val = Math.min(val, edge[2]);
            grid[u].put(v, val);
            grid[v].put(u, val);
        }
        for (int i = 0; i < queries.length; i++) {
            answer[i] = dfs(i + 1, queries[i][0], queries[i][1], queries[i][2]);
        }
        return answer;
    }

    private boolean dfs(int time, int p, int q, int limit) {
        if (p == q) {
            return true;
        }
        if (vis[p] == time) {
            return false;
        }
        vis[p] = time;
        boolean ans = false;
        for (Map.Entry<Integer, Integer> entry : grid[p].entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if (val < limit) {
                ans = dfs(time, key, q, limit);
            }
            if (ans) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CheckingExistenceOfEdgeLengthLimitedPaths existence = new CheckingExistenceOfEdgeLengthLimitedPaths();
        System.out.println(Arrays.toString(existence.distanceLimitedPathsExist(5, new int[][]{{0, 1, 10}, {1, 2, 5}, {2, 3, 9}, {3, 4, 13}}, new int[][]{{0, 4, 14}, {1, 4, 13}})));
    }

}
