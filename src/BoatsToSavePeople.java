import java.util.Arrays;

/**
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 *
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 *
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/boats-to-save-people
 */
public class BoatsToSavePeople {
    /**
     * 执行用时: 16 ms
     * 内存消耗: 47.4 MB
     */
    public static int numRescueBoats1(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1, count = 0;
        while (left < right) {
            if (people[right] + people[left] <= limit) {
                left++;
            }
            count++;
            right--;
        }
        return left == right ? ++count : count;
    }

    /**
     * 执行用时: 2 ms
     * 内存消耗: 46.7 MB
     */
    public static int numRescueBoats(int[] people, int limit) {
        int[] buckets = new int[limit + 1];
        for (int person : people) {
            buckets[person]++;
        }
        int left = 1, right = limit, count = 0;
        while (left < right) {
            int min = buckets[right];
            if (right + left <= limit) {
                min = Math.min(buckets[right], buckets[left]);
                buckets[left] -= min;
            }
            count += min;
            buckets[right] -= min;

            while (left < right && buckets[left] == 0) {
                left++;
            }
            while (left < right && buckets[right] == 0) {
                right--;
            }
        }
        if (left == right) {
            if (left * 2 <= limit) {
                count += (buckets[left] + 1) / 2;
            } else {
                count += buckets[left];
            }
        }
        return count;
    }
}
