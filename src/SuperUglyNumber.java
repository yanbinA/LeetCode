import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 *
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 *
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-ugly-number
 */
public class SuperUglyNumber {

    public static int nthSuperUglyNumber1(int n, int[] primes) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        long res = 0;
        queue.add(1L);
        seen.add(1L);
        for (int i = 0; i < n; i++) {
            res = queue.poll();
            for (int prime : primes) {
                long next = prime * res;
                if (seen.add(next)) {
                    queue.add(next);
                }
            }
        }
        return (int) res;
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int length = primes.length;
        int[] pointers = new int[length];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; i++) {
            int[] nums = new int[length];
            int minnum = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                nums[j] = dp[pointers[j]] * primes[j];
                minnum = Math.min(nums[j], minnum);
            }
            dp[i] = minnum;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == minnum) {
                    pointers[j]++;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(nthSuperUglyNumber(3, new int[]{3, 7}));
        System.out.println(nthSuperUglyNumber1(3, new int[]{3, 7}));
    }
}
