import java.util.*;

/**
 * <p>
 * 2039. 网络空闲的时刻
 * 给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息。再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。
 *
 * 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
 *
 * 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，并等待回复。信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。
 *
 * 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。从第 1 秒开始，每 一秒最 开始 时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
 *
 * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。
 * 否则，该数据服务器 不会重发 信息。
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。
 *
 * 请返回计算机网络变为 空闲 状态的 最早秒数 。
 * </p>
 *
 * @author messi
 * @package PACKAGE_NAME
 * @description the-time-when-the-network-becomes-idle
 * @date 2022-03-20 15:47
 * @verison V1.0.0
 */
public class TheTimeWhenTheNetworkBecomesIdle {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
        for (int[] v : edges) {
            adj[v[0]].add(v[1]);
            adj[v[1]].add(v[0]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        boolean[] read = new boolean[patience.length];
        read[0] = true;
        int maxTime = 0;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                for (int target : adj[index]) {
                    if (read[target]) {
                        continue;
                    }
                    queue.offer(target);
                    maxTime = Math.max(maxTime, (level + 1) * 2 + ((2 * level + 1) / patience[target]) * patience[target]);
                    read[target] = true;
                }
            }
            level++;
        }
        return maxTime + 1;

    }

    public static void main(String[] args) {
        TheTimeWhenTheNetworkBecomesIdle idle = new TheTimeWhenTheNetworkBecomesIdle();
        System.out.println(idle.networkBecomesIdle(new int[][]{{0, 1}, {1, 2}},
                new int[]{0, 2, 1}));
        System.out.println(idle.networkBecomesIdle(new int[][]{{0, 1}, {0, 2}, {1, 2}},
                new int[]{0, 12, 11}));
        System.out.println(idle.networkBecomesIdle(new int[][]{{5, 7}, {15, 18}, {12, 6}, {5, 1}, {11, 17}, {3, 9}, {6, 11}, {14, 7}, {19, 13}, {13, 3}, {4, 12}, {9, 15}, {2, 10}, {18, 4}, {5, 14}, {17, 5}, {16, 2}, {7, 1}, {0, 16}, {10, 19}, {1, 8}},
                new int[]{0, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1}));
    }
}
