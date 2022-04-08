package dp;



/**
 * <h3>爬楼梯</h3>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 */
public class ClimbingStairs {
    
    public void test() {
        System.out.println(climbStairs(1));//1
        System.out.println(climbStairs(2));//11,2
        System.out.println(climbStairs(3));//111,12,21
        System.out.println(climbStairs(4));//1111,121,211,112,22
        System.out.println(climbStairs(5));//11111,1211,2111,1121,221,1112,122,212
        System.out.println(climbStairs(6));//11111,1211,2111,1121,221,1112,122,212
    }


    public int climbStairs(int n) {
        int a = 0;
        int b = 1;
        int c = b;
        for (int i = 1; i <= n; i++) {
            c = b + a;
            a = b;
            b = c;
        }
        return c;
    }
}
