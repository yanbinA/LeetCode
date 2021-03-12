package numIslands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int numIslands(char[][] grid) {
        List<int[]> islandPoint = new ArrayList<>();
        int numIslands = 0;
        for (char[] chars : grid) {
            boolean start = true;
            List<int[]> xIslands = new ArrayList<>();
            int[] xIsland = {-1, -1};
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    if (start) {
                        start = false;
                        xIsland[0] = i;
                    }
                } else {
                    if (!start) {
                        xIsland[1] = i - 1;
                        start = true;
                        xIslands.add(xIsland);
                        xIsland = new int[2];
                    }
                }
            }
            if (!start) {
                xIsland[1] = chars.length - 1;
                xIslands.add(xIsland);
            }
            numIslands += xIslands.size();
            numIslands -= comparater(islandPoint, xIslands);
            islandPoint = xIslands;
        }
        return numIslands;
    }

    private int comparater(List<int[]> islandPoint, List<int[]> xIslands) {
        int num = 0;
        int j = 0;
        before:
        for (int i = 0; i < islandPoint.size(); i++) {
            int[] ints = islandPoint.get(0);
            for (; j < xIslands.size(); j++) {
                int[] xIsland = xIslands.get(j);
                if (ints[0] >= xIsland[0]) {
                    if (ints[0] <= xIsland[1]) {
                        num++;
                    }
                } else {
                    if (ints[1] >= xIsland[0]) {
                        num++;
                    } else {
                        continue before;
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
/*        11110
        11010
        11000
        00000*/
        char[][] grid = {
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '0', '1'},
                {'0', '0', '0', '0', '0'}};
        System.out.println(solution.isHappy(19));
    }

    public boolean isHappy(int n) {
        Set<Integer> list = new HashSet<>();
        list.add(n);
        return option(n, list);
    }

    private boolean option(int n, Set<Integer> list) {
        int tmp = 0;
        while (n > 0) {
            int i = n % 10;
            tmp += i * i;
            n = n / 10;
        }
        if (tmp == 1) {
            return true;
        } else {
            if (list.contains(tmp)) {
                return false;
            }
            list.add(tmp);
            return option(tmp, list);
        }
    }
}
