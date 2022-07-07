import java.util.Random;

/**
 *497. 非重叠矩形中的随机点
 * 给定一个由非重叠的轴对齐矩形的数组 rects ，其中 rects[i] = [ai, bi, xi, yi] 表示 (ai, bi) 是第 i 个矩形的左下角点，(xi, yi) 是第 i 个矩形的右上角角点。设计一个算法来随机挑选一个被某一矩形覆盖的整数点。矩形周长上的点也算做是被矩形覆盖。所有满足要求的点必须等概率被返回。
 *
 * 在一个给定的矩形覆盖的空间内任何整数点都有可能被返回。
 *
 * 请注意 ，整数点是具有整数坐标的点。
 *
 * 实现 Solution 类:
 *
 * Solution(int[][] rects) 用给定的矩形数组 rects 初始化对象。
 * int[] pick() 返回一个随机的整数点 [u, v] 在给定的矩形所覆盖的空间内。
 * 提示：
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -10^9<= ai< xi<=  10^9
 * - 10^9 <= bi < yi <=  10^9
 * xi - ai <= 2000
 * yi - bi <= 2000
 * 所有的矩形不重叠。
 * pick 最多被调用 104 次。
 *
 */
public class RandomPointInNonOverlappingRectangles {
    static class Solution {
        int[] totals;
        int sum;
        Random random = new Random();
        int[][] rects;

        public Solution(int[][] rects) {
            int n = rects.length;
            this.rects = rects;
            this.totals = new int[n];
            this.sum = 0;
            for (int i = 0; i < n; i++) {
                this.totals[i] = this.sum += (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            }
        }

        public int[] pick() {
            int r = random.nextInt(sum) + 1;
            int left = 0, right = totals.length - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (totals[mid] > r) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int[] rect = this.rects[left];
            int width = rect[2] - rect[0] + 1;
            int height = rect[3] - rect[1] + 1;
            int area = r - totals[left] + width * height;
            return new int[]{area % width   + rect[0], area / width + rect[1]};
        }
    }
}
