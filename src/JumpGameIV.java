import java.util.*;

/**
 * <h3>1345. 跳跃游戏 IV</h3>
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 *
 * 每一步，你可以从下标 i 跳到下标：
 *
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 *
 * 注意：任何时候你都不能跳到数组外面。
 */
public class JumpGameIV {
    public int minJumps(int[] arr) {
        int length = arr.length;
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            indexMap.putIfAbsent(arr[i], new ArrayList<>());
            indexMap.get(arr[i]).add(i);
        }
        LinkedList<int[]> nodes = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        nodes.add(new int[]{0, 0});
        while (!nodes.isEmpty()) {
            int[] poll = nodes.poll();
            int index = poll[0], jump = poll[1];
            if (index == length - 1) {
                return jump;
            }
            jump++;
            int next = index + 1;
            if (next < length && visited.add(next)) {
                nodes.add(new int[]{next, jump});
            }
            int before = index - 1;
            if (before > 0 && visited.add(before)) {
                nodes.add(new int[]{before, jump});
            }
            List<Integer> list = indexMap.get(arr[index]);
            if (list != null) {
                for (int same : list) {
                    if (same != index && visited.add(same)) {
                        nodes.add(new int[]{same, jump});
                    }
                }
                indexMap.remove(arr[index]);
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        JumpGameIV gameIV = new JumpGameIV();
        System.out.println(gameIV.minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404}));
        System.out.println(gameIV.minJumps(new int[]{6,1,9}));
        System.out.println(gameIV.minJumps(new int[]{7}));
    }
}
