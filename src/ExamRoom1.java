/**
 * 855. 考场就座
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 *
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 */
public class ExamRoom1 {
    //列表seat表示有人的位置
    //当调用seat()时,遍历seat就可以得到答案
    //在seat中,如果编号0没被占,会有idx=0,dist=seat[0]
    //在seat中,如果编号N-1没被占,会有idx=N-1,dist=N-1-seat[tail]
    //遍历seat,idx=seat[i-1]+(seat[i]-seat[i-1])/2,dist=idx-seat[i-1]
    //返回dist最小的idx
    Node head;
    Node tail;
    int n;
    public ExamRoom1(int n) {
        head = new Node(-1);
        this.n = n;
    }

    public int seat() {
        if (head.next == null) {
            head.next = new Node(0);
            return 0;
        }
        int maxDist = 0;
        int idx = 0;
        Node prev = head;
        if (head.next.val != 0) {
            maxDist = head.next.val;
        }
        Node cur = head.next;
        while (cur.next != null) {
            int curDist = (cur.next.val - cur.val) / 2;
            if (curDist > maxDist) {
                idx = cur.val + (cur.next.val - cur.val) / 2;
                maxDist = curDist;
                prev = cur;
            }
            cur = cur.next;
        }
        if (cur.val != n - 1) {
            if (maxDist < n - 1 - cur.val) {
                idx = n - 1;
                prev = cur;
            }
        }
        Node node = new Node(idx);
        node.next = prev.next;
        prev.next = node;
        return idx;
    }

    public void leave(int p) {
        System.out.println("leave " + p);
        Node prev = head;
        while (prev.next != null && prev.next.val != p) {
            prev = prev.next;
        }
        assert prev.next != null;
        prev.next = prev.next.next;
    }


    public static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }


}
