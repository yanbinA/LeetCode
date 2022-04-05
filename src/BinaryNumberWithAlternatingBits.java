/**
 *693. 交替位二进制数
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 */
public class BinaryNumberWithAlternatingBits {
    public boolean hasAlternatingBits(int n) {

        while (n > 0) {
            int bit = n % 2;
            n = n >> 1;
            if ((bit + 1) % 2 != n % 2) {
                return false;
            }
        }
        return true;
    }
}
