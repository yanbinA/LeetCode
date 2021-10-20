/**
 * <h3>数字的补数</h3>
 * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反
 */
public class NumberComplement {
    public static int findComplement(int num) {
        int n = num;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n ^ num;
    }

    public static void main(String[] args) {
        System.out.println(findComplement(5));
    }
}
