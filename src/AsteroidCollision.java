import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 735. 行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> list = new LinkedList<>();

        for (int asteroid : asteroids) {
            if (list.isEmpty()) {
                list.offer(asteroid);
            } else {
                while (!list.isEmpty()) {
                    int last = list.peekLast();
                    if (last > 0 && asteroid < 0) {
                        if (last > -asteroid) {
                            break;
                        }
                        if (last == -asteroid) {
                            list.pollLast();
                            break;
                        }
                        list.pollLast();
                        if (list.isEmpty()) {
                            list.offer(asteroid);
                            break;
                        }
                    } else {
                        list.offer(asteroid);
                        break;
                    }
                }
            }
        }
        int[] ans = new int[list.size()];
        Iterator<Integer> iterator = list.iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            ans[idx++] = iterator.next();
        }
        return ans;
    }

    public static void main(String[] args) {
        AsteroidCollision asteroidCollision = new AsteroidCollision();
        System.out.println(Arrays.toString(asteroidCollision.asteroidCollision(new int[]{5, 10, -5})));
    }
}
