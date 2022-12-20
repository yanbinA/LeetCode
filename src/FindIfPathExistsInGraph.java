/**
 * 1971. 寻找图中是否存在路径
 * 有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 *
 * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
 *
 * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
 */
public class FindIfPathExistsInGraph {
    int[] arr;
//    int[] size;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        arr = new int[n];
//        size = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
//            size[i] = 1;
        }
        for (int[] edge : edges) {
            this.merge(edge[0], edge[1]);
            if (find(source) == find(destination)) {
                return true;
            }
        }
        return find(source) == find(destination);
    }

    public int find(int x) {
        if (arr[x] == x) {
            return x;
        }
        return arr[x] = find(arr[x]);
    }

    public void merge(int x, int y) {
        arr[find(x)] = find(y);
//        x = find(x);
//        y = find(y);
//        if (x != y) {
//            if (size[x] > size[y]) {
//                arr[y] = x;
//                size[x] += size[y];
//            } else {
//                arr[x] = y;
//                size[y] += size[x];
//            }
    }

}
