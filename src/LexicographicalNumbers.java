import java.util.ArrayList;
import java.util.List;

/**
 * 386. 字典序排数
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 *
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            dfs(i, i + 1, n, ans);
        }
        return ans;
    }

    private void dfs(int cur, int next, int n, List<Integer> ans) {
        if (cur > n) {
            return;
        }
        ans.add(cur);
        cur *= 10;
        next *= 10;
        while (cur < next && cur <= n) {
            dfs(cur++, cur, n, ans);
        }
    }
}
