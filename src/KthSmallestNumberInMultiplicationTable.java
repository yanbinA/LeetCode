import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 668. 乘法表中第k小的数
 * 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 *
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 */
public class KthSmallestNumberInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int x = left + (right - left) / 2;
            int count = x / n * n;
            for (int i = x / n + 1; i <= m; ++i) {
                count += x / i;
            }
            if (count >= k) {
                right = x;
            } else {
                left = x + 1;
            }
        }
        return left;

    }

    public static void main(String[] args) {
        KthSmallestNumberInMultiplicationTable table = new KthSmallestNumberInMultiplicationTable();
        System.out.println(table.findKthNumber(45, 12, 471));
        System.out.println(table.findKthNumber(9895, 28405, 100787757));
    }

    // (mul / i - (mul % i) == 0? 1 : 0) - 1

}
