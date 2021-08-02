import java.util.*;

/**
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>(n);
        for (int[] time : times) {
            int key = time[0];
            if (map.containsKey(key)) {
                map.get(key).add(time);
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(time);
                map.put(key, list);
            }
        }
        int[] delays = new int[n];
        Arrays.fill(delays, -1);
        return -1;





    }
}
