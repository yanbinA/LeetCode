import java.util.Arrays;
import java.util.Comparator;

/**
 * 757. 设置交集大小至少为2
 * 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 */
public class SetIntersectionSizeAtLeastTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]));
        int res = 2;
        int end = intervals[0][1];
        int preEnd = end - 1;
        for (int[] num: intervals) {
            if (num[0] <= preEnd) { //left <= preEnd < end <= right【无需添加元素】
                continue;
            }
            if (num[0] <= end) { //preEnd < left <= end
                res++;
                preEnd = end;
                end = num[1];
            } else { // end < left < right
                res += 2;
                preEnd = num[1] - 1;
                end = num[1];
            }
        }
        return res;
    }
    //[[1,3],[1,4],[2,5],[3,5]]
    //[[8,9],[4,21],[3,19],[5,9],[1,5]]
    //[1,5],[3,19],[4,21],[5,9],[8,9]
    //[4,5],[]

    public static void main(String[] args) {
        SetIntersectionSizeAtLeastTwo leastTwo = new SetIntersectionSizeAtLeastTwo();
        //[[2,10],[3,7],[3,15],[4,11],[6,12],[6,16],[7,8],[7,11],[7,15],[11,12]]
//        System.out.println(leastTwo.intersectionSizeTwo(new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}}));
//        System.out.println(leastTwo.intersectionSizeTwo(new int[][]{{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}}));
//        System.out.println(leastTwo.intersectionSizeTwo(new int[][]{{4,14},{6,17},{7,14},{14,21},{4,7}}));
        System.out.println(leastTwo.intersectionSizeTwo(new int[][]{{0,3},{0,4},{0,9},{8,9},{0,7},{1,4},{6,10},{0,4},{3,7},{6,8}}));
        //[[4,14},[6,17],[7,14],[14,21],[4,7]]
        //[4,7][7,14][14,21]
        //[[0,3],[0,4],[0,9],[8,9],[0,7],[1,4],[6,10],[0,4],[3,7],[6,8]]

    }
}
