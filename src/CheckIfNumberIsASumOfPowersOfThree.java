import java.util.*;

/**
 * 1780. 判断一个数字是否可以表示成三的幂的和
 * 给你一个整数 n ，如果你可以将 n 表示成若干个不同的三的幂之和，请你返回 true ，否则请返回 false 。
 *
 * 对于一个整数 y ，如果存在整数 x 满足 y == 3x ，我们称这个整数 y 是三的幂。
 */
public class CheckIfNumberIsASumOfPowersOfThree {
    int[] tb = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969};
    public boolean checkPowersOfThree(int n) {
        int right = tb.length - 1;
        while (right >= 0) {
            if (n >= tb[right]) {
                n -= tb[right];
                if (n == 0) {
                    return true;
                }
                if (n >= tb[right]) {
                    return false;
                }
            }
            right--;
        }
        return false;
    }

    public static void main(String[] args) {
        CheckIfNumberIsASumOfPowersOfThree three = new CheckIfNumberIsASumOfPowersOfThree();
        System.out.println(three.dividePlayers(new int[]{1, 2, 3, 3, 4, 5}));
    }

    public boolean isCircularSentence(String sentence) {
        String[] s = sentence.split(" ");
        int n = s.length;
        for (int i = 0; i < n - 1; i++) {
            if (s[i].charAt(s[i].length() - 1) != s[i + 1].charAt(0)) {
                return false;
            }
        }
        return s[0].charAt(0) == s[n - 1].charAt(s[n - 1].length() - 1);
    }

    public long dividePlayers(int[] skill) {
        long res = 0;
        int n = skill.length;
        int team = n / 2;
        int[] tb = new int[1001];
        int sum = 0;
        for (int num : skill) {
            sum += num;
            tb[num]++;
        }
        if (sum % team != 0) {
            return -1;
        }
        int avg = sum / team;
        for (int i = 1; i <= avg / 2; i++) {
            if (avg - i > 1000) {
                continue;
            }
            if (tb[avg - i] != tb[i]) {
                return -1;
            }
            if (avg - i == i) {
                tb[i] /= 2;
            }
            res += (long) tb[i] * (avg - i) * i;
        }
        return res;
    }

    public int minScore(int n, int[][] roads) {
        int min = 10000;
        boolean[] visited = new boolean[n + 1];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] road : roads) {

            if (map.containsKey(road[0])) {
                map.get(road[0]).add(new int[]{road[1], road[2]});
            } else {
                List<int[]> list = new ArrayList<>(){{add(new int[]{road[1], road[2]});}};
                map.put(road[0], list);
            }
            if (map.containsKey(road[1])) {
                map.get(road[1]).add(new int[]{road[0], road[2]});
            } else {
                List<int[]> list = new ArrayList<>(){{add(new int[]{road[0], road[2]});}};
                map.put(road[1], list);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (!map.containsKey(idx)) {
                continue;
            }
            if (visited[idx]) {
                continue;
            }
            List<int[]> list = map.get(idx);
            for (int[] ints : list) {
                min = Math.min(min, ints[1]);
                queue.offer(ints[0]);
            }
            visited[idx] = true;
        }
        return min;
    }

    private int dfs(int idx) {
        int res = Integer.MAX_VALUE;

        return 0;
    }


}
