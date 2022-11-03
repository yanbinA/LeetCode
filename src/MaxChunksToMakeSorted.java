/**
 * 769. 最多能完成排序的块
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 *
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 *
 * 返回数组能分成的最多块数量。
 */
public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        int m = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            m = Math.max(m, arr[i]);
            if (m <= i) {
                ans++;
                m = 0;
            }
        }
        return ans;
    }
}
