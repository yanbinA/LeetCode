import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 *
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 */
public class MatchsticksToSquare {
    boolean[] visited;
    int n, avg;
    int[] nums;

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        visited = new boolean[matchsticks.length];

        Arrays.sort(matchsticks);
        n = matchsticks.length;
        nums = matchsticks;
        avg = sum >> 2;

        return dfs(n - 1, 0, 1);
    }

    private boolean dfs(int pos, int sum,  int count) {
        if (count == 4) {
            return true;
        }
        if (sum == avg) {
            return dfs(n - 1, 0, count + 1);
        }
        for (int i = pos; i >= 0; i--) {
            if (!visited[i]) {
                if (sum <= avg - nums[i] ) {
                    visited[i] = true;
                    if (dfs(i - 1, sum + nums[i], count)) {
                        return true;
                    }
                    visited[i] = false;
                }
                if (sum == 0 || sum == avg - nums[i]) {
                    return false;
                }
                while (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                    i--;
                }
            }
        }
        return false;
    }

    /**
     * 回溯
     * 41 ms, 在所有 Java 提交中击败了51.82%的用户
     */
    public boolean makesquare1(int[] matchsticks) {
        int sum = 0;
        Arrays.sort(matchsticks);
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }
        if (sum % 4 != 0) {
            return false;
        }
        return find1(matchsticks, sum >> 2, matchsticks.length - 1, new int[4]);
    }

    private boolean find1(int[] matchsticks, int avg, int pos, int[] bucket) {
        System.out.println(Arrays.toString(bucket));
        if (pos == -1) {
            return bucket[0] == avg && bucket[1] == avg && bucket[2] == avg && bucket[3] == avg;
        }
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + matchsticks[pos] > avg) {
                continue;
            }
            bucket[i] += matchsticks[pos];
            if (find1(matchsticks, avg, pos - 1, bucket)) {
                return true;
            }
            bucket[i] -= matchsticks[pos];
        }
        return false;
    }

    public static void main(String[] args) {

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        System.out.println(s3 == s4);

    }

    public void test(int a) {
        System.out.println("int" + a);
    }

    public void test(char a) {
        System.out.println("char" + a);
    }

    public void test(byte a) {
        System.out.println("byte" + a);
    }


}
