package number_of_1_bits;

import sun.security.util.BitArray;

/**
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        HammingWeight weight = new HammingWeight();
        System.out.println(weight.hammingWeight(-3));
    }

    //颠倒给定的 32 位无符号整数的二进制位。
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }
}
