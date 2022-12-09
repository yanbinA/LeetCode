import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 2493. 将节点分成尽可能多的组
 * 给你一个正整数 n ，表示一个 无向 图中的节点数目，节点编号从 1 到 n 。
 *
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 双向 边。注意给定的图可能是不连通的。
 *
 * 请你将图划分为 m 个组（编号从 1 开始），满足以下要求：
 *
 * 图中每个节点都只属于一个组。
 * 图中每条边连接的两个点 [ai, bi] ，如果 ai 属于编号为 x 的组，bi 属于编号为 y 的组，那么 |y - x| = 1 。
 * 请你返回最多可以将节点分为多少个组（也就是最大的 m ）。如果没办法在给定条件下分组，请你返回 -1 。
 */
public class DivideNodesIntoTheMaximumNumberOfGroups {
    /*
    在一个连通图中,最多可分的组m=连通图的最大深度d
    如果图中有入度为1的节点, 将改节点作为root节点计算最大深度
    图中没有为1的节点, 遍历各个节点作为root节点计算最大深度
    以节点node最为root, 广度优先遍历, 并标记已遍历节点的的深度
    visited[i]表示节点i在图中的深度, visited[i]=0表示没有遍历到
    当访问到节点i, 上级节点深度为dep时,
    1. visited[i] != 0 && dep - visited[i] != 1, 说明无法分组,提前结束改root节点的遍历
    2. visited[i] != 0 && dep - visited[i] == 1,节点已被访问,且符合条件. 跳过节点i.
    3. visited[i] == 0, 节点未被访问, visited[i] = dep + 1
    图是否能满足∣y−x∣=1 这个要求, 是否和root节点的选取有关系?
    将∣y−x∣=1 这个要求 转化为另一个问题:
    设定dep取值-1或1, 相连的节点值不能相同(将节点上色(红/蓝), 相连的节点颜色不能相同)
    遍历节点, 当访问到节点i, 上级节点颜色为color=1时,
    1. color[i] != 0 && dep == color[i], 说明无法分组,提前结束改root节点的遍历
    2. color[i] != 0 && dep != color[i] ,节点已被访问,且符合条件. 跳过节点i.
    3. color[i] == 0, 节点未被访问, color[i] = -color


    但是, 我们需要在枚举root节点的同时,还需要记录访问深度(通过访问深度判断是否可分组)
    vis[i]={time, dep}表示第time次操作时 访问i节点

    从头开始
    1. 遍历edges, 构建点到点关系grid[]
    2. 0-n遍历, 以i为起点递归获取联通图的节点,记录访问过的点防止获取到重复的图
    3. 判断连通图是否可以满足|x-y|=1的要求
    3. 枚举2中获取的节点,计算该联通图的最大深度
     */
    public List<Integer>[] grid;
    public int[] vis;
    public int[] color;
    public List<Integer> nodes;
    public int times;


    public int magnificentSets(int n, int[][] edges) {
        int res = 0;
        grid = new ArrayList[n];
        Arrays.setAll(grid, e -> new ArrayList<>());
        vis = new int[n];
        color = new int[n];
        for (int[] edge : edges) {
            grid[edge[0] - 1].add(edge[1] - 1);
            grid[edge[1] - 1].add(edge[0] - 1);
        }
        nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (color[i] != 0) {
                continue;
            }
            nodes.clear();
            if (!isDivide(i, 1)) {
                return -1;
            }
            int max = 0;
            for (int node : nodes) {
                max = Math.max(max, bfs(node));
            }
            res += max;
        }
        return res;
    }

    private int bfs(int node) {
        int depth = 0;
        vis[node] = ++times;
        List<Integer> queue = new ArrayList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            depth++;
            List<Integer> tem = queue;
            queue = new ArrayList<>();
            for (int idx : tem) {
                for (int y : grid[idx]) {
                    if (vis[y] != times) {
                        vis[y] = times;
                        queue.add(y);
                    }
                }
            }
        }
        return depth;
    }

    /*
    1. 判断是否能满足∣y−x∣=1 这个要求
    2. 向nodes添加节点
     */
    private boolean isDivide(int idx, int c) {
        nodes.add(idx);
        color[idx] = c;

        for (int child : grid[idx]) {
            if (color[child] == c || color[child] == 0 && !isDivide(child, -c)) {
                return false;
            }
        }
        return true;
    }
}
