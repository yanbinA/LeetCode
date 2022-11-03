package lccup_2021_fall;

import java.util.Arrays;

public class Solution {
    /**
     * LCP 39. 无人机方阵
     * 在 「力扣挑战赛」 开幕式的压轴节目 「无人机方阵」中，每一架无人机展示一种灯光颜色。 无人机方阵通过两种操作进行颜色图案变换：
     *
     * 调整无人机的位置布局
     * 切换无人机展示的灯光颜色
     * 给定两个大小均为 N*M 的二维数组 source 和 target 表示无人机方阵表演的两种颜色图案，由于无人机切换灯光颜色的耗能很大，请返回从 source 到 target 最少需要多少架无人机切换灯光颜色。
     *
     * 注意： 调整无人机的位置布局时无人机的位置可以随意变动。
     * 提示：
     * n == source.length == target.length
     * m == source[i].length == target[i].length
     * 1 <= n, m <=100
     * 1 <= source[i][j], target[i][j] <=10^4
     */
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int[] c = new int[10001];
        for (int[] sx : source) {
            for (int x : sx) {
                c[x]++;
            }
        }
        int sum = 0;
        for (int[] sx : target) {
            for (int x : sx) {
                c[x]--;
                if (c[x] < 0) {
                    sum++;
                }
            }
        }
        return sum;
    }


    /**
     * LCP 40. 心算挑战
     * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
     * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
     * 提示：
     *
     * 1 <= cnt <= cards.length <= 10^5
     * 1 <= cards[i] <= 1000
     */
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int minOdd = 0;
        int minEven = 0;
        int n = cards.length;
        int sum = 0;
        for (int i = n - 1; i >= n - cnt; i--) {
            int card = cards[i];
            sum += card;
            if (card % 2 == 0) {
                minEven = card;
            } else {
                minOdd = card;
            }
        }
        if (sum % 2 == 0) {
            return sum;
        }
        int maxOdd = 0;
        int maxEven = 0;
        boolean hasEven = minEven != 0;
        boolean hasOdd = minOdd != 0;
        for (int i = n - cnt - 1; i >= 0; i--) {
            if (hasEven || hasOdd) {
                int card = cards[i];
                if (card % 2 == 0) {
                    maxEven = card;
                    hasOdd = false;
                } else {
                    maxOdd = card;
                    hasEven = false;
                }
            } else {
                break;
            }
        }
        int ans = 0;
        if (minEven != 0 && maxOdd != 0) {
            ans = sum + maxOdd - minEven;
        }
        if (minOdd != 0 && maxEven != 0) {
            ans = Math.max(ans, sum  + maxEven - minOdd);
        }
        return ans;
    }
}
