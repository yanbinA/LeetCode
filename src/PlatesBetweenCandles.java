/**
 * <h3>2055. 蜡烛之间的盘子</h3>
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 *
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 */
public class PlatesBetweenCandles {

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int length = s.length();
        int[] latelyRights = new int[length], latelyLefts = new int[length], counts = new int[length];
        int ll = -1, lr = length, count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[length - i - 1] == '|') {
                lr = length - i - 1;
            }
            latelyRights[length - i - 1] = lr;
            if (chars[i] == '|') {
                ll = i;
                counts[i] = count;
            }
            if (chars[i] == '*') {
                counts[i] = ++count;
            }
            latelyLefts[i] = ll;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int latelyRight = latelyRights[l];
            int latelyLeft = latelyLefts[r];
            if (latelyRight < latelyLeft) {
                ans[i] = counts[latelyLeft] - counts[latelyRight];
            }
        }
        return ans;
    }
//"**|**|***|"
//[[2,5],[5,9]]
    public static void main(String[] args) {
        PlatesBetweenCandles candles = new PlatesBetweenCandles();
        candles.platesBetweenCandles("**|**|***|", new int[][]{{2,5}, {5,9}});
        candles.platesBetweenCandles("*****|***|", new int[][]{{2,5}, {5,9}});
    }
}
