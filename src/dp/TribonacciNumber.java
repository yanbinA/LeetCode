package dp;

/**
 * <h3>第n个泰波那契数</h3>
 * 泰波那契序列 Tn 定义如下： 
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 */
public class TribonacciNumber {
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0,b = 1, c=1;
        int res = 1;
        for (int i = 3; i <= n; i++) {
            res = a + b + c;
            a = b;
            b = c;
            c = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(tribonacci(0));
        System.out.println(tribonacci(1));
        System.out.println(tribonacci(2));
        System.out.println(tribonacci(3));
        System.out.println( tribonacci(4));
        System.out.println( tribonacci(5));
        System.out.println( tribonacci(6));
        System.out.println( tribonacci(7));
        System.out.println( tribonacci(8));
        System.out.println( tribonacci(9));
        System.out.println( tribonacci(10));
    }
}
