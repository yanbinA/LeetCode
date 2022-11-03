/**
 * 779. 第K个语法符号
 * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 *
 * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
 * 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
 */
public class KThSymbolInGrammar {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        if (k == 1) {
            return 0;
        }
        int ans = kthGrammar(n - 1, (k + 1) / 2);
        if (k % 2 == 0) {
            ans = (ans + 1) % 2;
        }
        return ans;
    }
    //0
    //01
    //0110
    //01101001
    //0110100110010110
    //01101001100101101001011001101001
}
