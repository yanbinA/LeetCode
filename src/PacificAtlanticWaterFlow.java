import java.util.ArrayList;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 (同时)。
 */
public class PacificAtlanticWaterFlow {
    int[][] heights;
    int[][] flow;//水流向,1-太平洋,2-大西洋,3-太平洋&大西洋
    int m, n;
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        flow = new int[m][n];
        this.heights = heights;
        for (int i = 0; i < n; i++) {
            //上边界流入太平洋
            dfs(0, i, 1);
            //下边界流入大西洋
            dfs(m - 1, i, 2);
        }
        for (int i = 0; i < m; i++) {
            //左边界流入太平洋
            dfs(i, 0, 1);
            //右边界流入大西洋
            dfs(i, n - 1, 2);
        }
        return ans;
    }

    private void dfs(int x, int y, int target) {
        //已被访问过不处理
        if ((target & flow[x][y]) != 0) {
            return;
        }
        flow[x][y] |= target;
        if (flow[x][y] == 3) {
            List<Integer> list = new ArrayList<>();
            list.add(x);
            list.add(y);
            ans.add(list);
        }
        //继续搜索四个方向
        if (m > x + 1 && heights[x + 1][y] >= heights[x][y])
            dfs(x + 1, y, target);
        if (0 <= x - 1 && heights[x - 1][y] >= heights[x][y])
            dfs(x - 1, y, target);
        if (n > y + 1 && heights[x][y + 1] >= heights[x][y])
            dfs(x, y + 1, target);
        if (0 <= y - 1 && heights[x][y - 1] >= heights[x][y])
            dfs(x, y - 1, target);
    }

    private void dfs(int x, int y, int target, int height) {
        //超出边界不处理
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        //高度小于height不处理
        if (heights[x][y] < height) {
            return;
        }
        //已被访问过不处理
        if ((target & flow[x][y]) != 0) {
            return;
        }
        flow[x][y] |= target;
        if (flow[x][y] == 3) {
            List<Integer> list = new ArrayList<>();
            list.add(x);
            list.add(y);
            ans.add(list);
        }
        //继续搜索四个方向
        dfs(x + 1, y, target, heights[x][y]);
        dfs(x - 1, y, target, heights[x][y]);
        dfs(x, y + 1, target, heights[x][y]);
        dfs(x, y - 1, target, heights[x][y]);
    }

}
