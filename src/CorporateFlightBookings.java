import java.util.Arrays;

/**
 * 航班预订系统
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 */
public class CorporateFlightBookings {
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answer = new int[n];
        for (int[] booking : bookings) {
            int first = Math.min(booking[0] - 1, n - 1);
            int last = Math.min(booking[1] - 1, n - 1);
            for (int j = first; j <= last; j++) {
                answer[j] += booking[2];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] booskings = new int[][]{{1,2,10},{2,3,20},{2,5,25}};
        System.out.println(Arrays.toString(corpFlightBookings(booskings, 5)));
    }
}
