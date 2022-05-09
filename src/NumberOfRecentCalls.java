import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 933. 最近的请求次数
 * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 *
 * 请你实现 RecentCounter 类：
 *
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 */
public class NumberOfRecentCalls {
    static class RecentCounter {
        int left;
        List<Integer> list;

        public RecentCounter() {
            this.left = 0;
            this.list = new ArrayList<>();
        }

        public int ping(int t) {
            int last = t - 3000;
            list.add(t);
            int n = list.size();
            for (; left < n; left++) {
                if (list.get(left) >= last) {
                    break;
                }
            }
            return n - left;
        }
    }

    public static void main(String[] args) {
        RecentCounter counter = new RecentCounter();
        counter.ping(475);
        counter.ping(3573);
        counter.ping(4015);
        counter.ping(4918);
        counter.ping(5098);
        counter.ping(5686);
    }
}
