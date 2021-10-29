package dp;

/**
 * <h3>1014. 最佳观光组合</h3>
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 */
public class BestSightseeingPair {
    public static int maxScoreSightseeingPair(int[] values) {
        int max = values[0], result = 0;
        for (int i = 1; i < values.length; i++) {
            result = Math.max(result, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return result;
    }

    public static int maxScoreSightseeingPair1(int[] values) {
        int length = values.length;
        int[] scores = new int[length];
        scores[length - 1] = values[length - 1] - length;
        //由后往前确认最大的第二个点
        for (int i = length - 2; i >= 0; i--) {
            scores[i] = Math.max(scores[i + 1],  values[i] - (i + 1));
        }
        int max = 0;
        for (int i = 0; i < values.length - 1; i++) {
            max = Math.max(max, values[i] + i + 1 + scores[i + 1]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxScoreSightseeingPair(new int[]{8, 1, 5, 2, 6}));
    }
}
