import java.util.Arrays;

/**
 * 878. 第 N 个神奇数字
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 *
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
 */
public class NthMagicalNumber {
    public static final int MOD = 1000000007;
    //根据f(x)=x/a + x/b - x /c,
    //f(x*c)=x(c/a + c/b - 1)=x*f(c), 其中c为a,b最小公倍数, x非负数
    //令m=f(c),n=x*m + r, 0<=0<m 因为不大于x*c的神奇数字的个数为x*m, 所以只需要从x*c往后搜第r个神奇数字就可以
    public int nthMagicalNumber(int n, int a, int b) {
        int c = lcm(a, b);
        int m = c / a + c / b - 1;
        int r = n % m;
        int ans = (int) (((long)c * (n / m)) % MOD);
        if (r == 0) {
            return ans;
        }
        int la = a,lb = b;
        for (int i = 0; i < r - 1; i++) {
            if (la < lb) {
                la += a;
            } else {
                lb += b;
            }
        }
        return (ans + Math.min(la, lb) % MOD) % MOD;
    }

    //容斥原理+二分法
    //f(x)为小于或等于x的神奇数字的个数,
    //f(x)=x/a + x/b - x /c, 其中c为a,b最小公倍数
    //f(x)是单调函数, 可以通过二分法来处理
    public int nthMagicalNumber2(int n, int a, int b) {
        long l = Math.min(a, b);
        long r = n * l;
        int c = lcm(a, b);
        while (l <= r) {
            long mid = l + (r - l) / 2;
            long count = mid / a + mid / b - mid / c;
            if (count >= n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) ((r + 1) % MOD);
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }


    //双指针,O(n)
    //由于n值非常大,该方法会超时
    public int nthMagicalNumber1(int n, int a, int b) {
        int[] queue = new int[n];
        int lb = 1, la = 1;
        int idx = 0;
        while (idx < n) {
            if (lb * b > la * a) {
                queue[idx++] = la * a;
                la++;
            } else if (lb * b < la * a) {
                queue[idx++] = lb * b;
                lb++;
            } else {
                queue[idx++] = lb * b;
                la++;
                lb++;
            }
        }
        System.out.println("queue:" + Arrays.toString(queue));
        return queue[n - 1];
    }

    public static void main(String[] args) {
        NthMagicalNumber number = new NthMagicalNumber();
        System.out.println(number.nthMagicalNumber(100, 2, 3));

    }

    //a a^2 b a^3 ab a^4 a^2b b^2
    //                        B                   A
    //a aa b aaa ab aaaa aab bb  aaaaa aaab abb aaaaaa  aaaab aabb aaaaab
    //1 2 0   3   1   4  2    0
}
