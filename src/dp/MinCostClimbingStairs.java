package dp;

/**
 * <h3>使用最小花费爬楼梯</h3>
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 *
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs1(int[] cost) {
        int[] minCost = new int[cost.length + 2];
        int length = minCost.length;
        for (int i = 2; i < length; i++) {
            minCost[i] = Math.min(minCost[i - 2], minCost[i - 1]) + cost[i - 2];
        }
        return Math.min(minCost[length - 1], minCost[length - 2]);
    }

    public int minCostClimbingStairs(int[] cost) {
        int a = 0, b = 0, c = 0;
        for (int j : cost) {
            a = b;
            b = c;
            c = Math.min(a, b) + j;
        }
        return Math.min(c, b);
    }
}
