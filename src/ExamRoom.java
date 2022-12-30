import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 855. 考场就座
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 *
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 */
public class ExamRoom {

    TreeSet<Integer> treeSet;
    int n;
    public ExamRoom(int n) {
        this.n = n - 1;
        treeSet = new TreeSet<>();
    }

    public int seat() {
        if (treeSet.isEmpty()) {
            treeSet.add(0);
            return 0;
        }
        int max = 0, pre = 0, start = 0, end = 0;
        for (Integer num : treeSet) {
            if ((num - pre) / 2 > max) {
                max = (num -pre) / 2;
                start = pre;
                end = num;
            }
            pre = num;
        }
        int left = treeSet.first(), right = n - treeSet.last();
        if (right > left && right > max) {
            treeSet.add(n);
            return n;
        }
        if (left >= max) {
            treeSet.add(0);
            return 0;
        }
        int idx = (start + end) >> 1;
        treeSet.add(idx);
        return idx;
    }

    public void leave(int p) {
        treeSet.remove(p);
    }

}
