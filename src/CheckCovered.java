import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个二维整数数组ranges和两个整数left和right。每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。
 *
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。
 */
public class CheckCovered {
    public static boolean isCovered2(int[][] ranges, int left, int right) {
        Arrays.sort(ranges, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int rangeLeft = ranges[0][0], rangeRight = ranges[0][1];
        for (int i = 1; i < ranges.length; i++) {
            if (ranges[i][0] > rangeRight + 1) {
                if (rangeLeft <= left && rangeRight >= right) {
                    return true;
                } else {
                    rangeLeft = ranges[i][0];
                }
            }
            rangeRight = Math.max(ranges[i][1], rangeRight);
        }
        return rangeLeft <= left && rangeRight >= right;
    }

    public static boolean isCovered(int[][] ranges, int left, int right) {
        return isCovered(0, ranges, left, right);
    }

    private static boolean isCovered(int i, int[][] ranges, int left, int right) {
        for (; i < ranges.length; i++) {
            int rangeLeft = ranges[i][0];
            int rangeRight = ranges[i][1];
            if (left > rangeRight || right < rangeLeft) {
                return isCovered(i + 1, ranges, left, right);
            }
            if (left >= rangeLeft && right <= rangeRight) {
                return true;
            }
            if (left < rangeLeft && right <= rangeRight) {
                return isCovered(i + 1, ranges, left, rangeLeft - 1);
            }
            if (left >= rangeLeft) {
                return isCovered(i + 1, ranges, rangeRight + 1, right);
            }
            return isCovered(i + 1, ranges, left, rangeLeft - 1) && isCovered(i + 1, ranges, rangeRight + 1, right);
        }
        return false;
    }

    public static void main(String[] args) {
        //[[25,42],[7,14],[2,32],[25,28],[39,49],[1,50],[29,45],[18,47]]
        System.out.println(isCovered(new int[][]{{1, 2}, {3, 4}, {5, 6}}, 1, 5));
        System.out.println(isCovered(new int[][]{{25,42},{7,14},{2,32},{25,28},{39,49},{1,50},{29,45},{18,47}}, 15, 38));
    }
}
