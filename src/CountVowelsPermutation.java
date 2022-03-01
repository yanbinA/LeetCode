import java.util.HashMap;
import java.util.Map;

/**
 * <h3>1220. 统计元音字母序列的数目</h3>
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'u' 后面只能跟着 'a'
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 *
 */
public class CountVowelsPermutation {
    static final int MOD = 1000000007;
    int[][] nodes;
    public int countVowelPermutation(int n) {
        this.nodes = new int[3][n];
        nodes[0][0] = 1;
        nodes[1][0] = 1;
        nodes[2][0] = 1;
        int count = 0;
        count = (count + this.getNode(0, n - 3)) % MOD;
        count = (count + this.getNode(0, n - 2)) % MOD;
        count = (count + this.getNode(0, n - 1)) % MOD;
        count = (count + this.getNode(1, n - 1)) % MOD;
        count = (count + this.getNode(2, n - 1)) % MOD;
        return count;
    }

    private int getNode(int x, int y) {
        if (y < 0) {
            return 1;
        }
        if (nodes[x][y] == 0) {
            int count = 0;
            switch (x) {
                case 0:
                    int ea = getNode(0, y - 2);
                    int ei = getNode(1, y - 1);
                    count = (ea + ei) % MOD;
                    break;
                case 1:
                    int iu = getNode(0, y - 3);
                    int ia = getNode(0, y - 2);
                    int ie = getNode(0, y - 1);
                    int io = getNode(2, y - 1);
                    count = ((ia + ie) % MOD + (io + iu) % MOD) % MOD;
                    break;
                case 2:
                    int ou = getNode(0, y - 3);
                    int oi = getNode(1, y - 1);
                    count = (oi + ou) % MOD;
                    break;
                default:
                    break;
            }
            nodes[x][y] = count;
        }
        return nodes[x][y];
    }

    public static void main(String[] args) {
        CountVowelsPermutation countVowelsPermutation = new CountVowelsPermutation();
        System.out.println(countVowelsPermutation.countVowelPermutation(10));
        System.out.println(countVowelsPermutation.countVowelPermutation(2));
        System.out.println(countVowelsPermutation.countVowelPermutation(5));
    }


    //I(n) = E(n-3) + E(n-2) + E(n-1) + O(n-1)
    // = E(n-3) + E(n-2) + E(n-1) + E(n-4) + I(n-2)
    //E(n) = E(n-2) + I(n-1)
    //O(n) = E(n-3) + I(n-1)
    //A(n) = E(n-1)
    //U(n) = E(n-2)
}
