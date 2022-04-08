import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 找到最终的安全状态
 * 在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
 *
 * 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
 *
 * 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
 *
 * 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，满足 (i, j) 是图的一条有向边。
 */
public class FindEventualSafeStates {
    static Set<Integer> rounds = new HashSet<>();
    static Set<Integer> safes = new HashSet<>();
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (rounds.contains(i)) {
                continue;
            }
            if (safes.contains(i)) {
                list.add(i);
                continue;
            }
            Set<Integer> paths = new HashSet<>();
            Set<Integer> currentSafes = new HashSet<>();
            if (dfs(i, graph, paths, currentSafes)) {
                list.add(i);
                safes.addAll(currentSafes);
            };
        }
        return list;
    }

    private static boolean dfs(int i, int[][] graph, Set<Integer> paths, Set<Integer> currentSafes) {
        currentSafes.add(i);
        if (rounds.contains(i)) {
            return false;
        }
        if (!paths.add(i)) {
            rounds.addAll(paths);
            return false;
        }
        for (int j : graph[i]) {
            if (!dfs(j, graph, paths, currentSafes)) {
                return false;
            }
        }
        paths.remove(i);
        return true;
    }

    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}}));
        rounds = new HashSet<>();
        safes = new HashSet<>();
        System.out.println(eventualSafeNodes(new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}}));
    }

}
