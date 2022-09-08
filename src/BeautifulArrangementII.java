/**
 * 667. 优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 *
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 */
public class BeautifulArrangementII {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        ans[0] = k / 2 + 1;
        int up = k % 2 == 1 ? 1 : -1;
        int i = 1;
        for (; i <= k; i++) {
            ans[i] = ans[i - 1] + i * up;
            up *= -1;
        }
        for (; i < n; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }

    //1,2,3,4,5,6,7    k=1
    //2,1,3,4,5,6,7    k=2
    //2,3,1,4,5,6,7    k=3
    //3,2,4,1,5,6,7    k=4
    //3,4,2,5,1,6,7    k=5
    //4,3,5,2,6,1,7    k=6
}
