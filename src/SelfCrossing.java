import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *335. 路径交叉
 * 给你一个整数数组 distance 。
 *
 * 从 X-Y 平面上的点 (0,0) 开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
 *
 * 判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
 */
public class SelfCrossing {
    public static boolean isSelfCrossing(int[] distance) {
        int x = 0, y = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < distance.length; i++) {
            switch (i % 4) {
                case 0:
                    Set<Integer> set = map.getOrDefault(x, new HashSet<>());
                    for (int j = 0; j < distance[i]; j++) {
                        if (!set.add(y++)) {
                            return true;
                        }
                    }
                    map.put(x, set);
                    break;
                case 1:
                    for (int j = 0; j < distance[i]; j++) {
                        if (map.containsKey(x)) {
                            if (!map.get(x).add(y)) {
                                return true;
                            }
                        } else {
                            Set<Integer> hashSet = new HashSet<>();
                            hashSet.add(y);
                            map.put(x, hashSet);
                        }
                        x--;
                    }
                    break;
                case 2:
                    Set<Integer> set1 = map.getOrDefault(x, new HashSet<>());
                    for (int j = 0; j < distance[i]; j++) {
                        if (!set1.add(y--)) {
                            return true;
                        }
                    }
                    map.put(x, set1);
                    break;
                case 3:
                    for (int j = 0; j < distance[i]; j++) {
                        if (map.containsKey(x)) {
                            if (!map.get(x).add(y)) {
                                return true;
                            }
                        } else {
                            Set<Integer> hashSet = new HashSet<>();
                            hashSet.add(y);
                            map.put(x, hashSet);
                        }
                        x++;
                    }
                    break;
                default:
            }
        }
        if (map.containsKey(x)) {
            return !map.get(x).add(y);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isSelfCrossing(new int[]{3,3,4,2,2}));
        int[] distance = new int[2000];
        for (int i = 0; i < 1000; i++) {
            distance[i * 2] = i + 1;
            distance[i * 2 + 1] = i + 1;
        }
        System.out.println(isSelfCrossing(distance));
    }
}
