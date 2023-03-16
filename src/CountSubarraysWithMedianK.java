import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *2488. 统计中位数为 K 的子数组
 * 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。
 *
 * 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
 *
 * 注意：
 *
 * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
 * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
 * 子数组是数组中的一个连续部分。
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,1,4,5], k = 4
 * 输出：3
 * 解释：中位数等于 4 的子数组有：[4]、[4,5] 和 [1,4,5] 。
 */
public class CountSubarraysWithMedianK {
    boolean[][] visited;
    int[] nums;
    int k;

    //以k值的位置为原点, 左右滑动窗口统计个数
    //超出内存限制
    public int countSubarrays1(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        visited = new boolean[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (k == nums[i]) {
                count = find(i, i, 0, 0, true);
            }
        }
        return count;
    }

    private int find(int left, int right, int down, int up, boolean dir) {
        if (visited[left][right]) {
            return 0;
        }
        int temp;
        if (dir) {
            temp = nums[right];
        } else {
            temp = nums[left];
        }
        if (temp > k) {
            up++;
        } else {
            down++;
        }
        int count = 0;
        if (up == down || down == up + 1) {
            count++;
        }
        if (left > 0) {
            count += find(left - 1, right, down, up, false);
        }
        if (right < nums.length - 1) {
            count += find(left, right + 1, down, up, true);
        }
        visited[left][right] = true;
        return count;
    }



    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int kIdx = -1;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            if (k == nums[i]) {
                kIdx = i;
                break;
            }
            sum += nums[i] < k ? -1 : 1;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        for (int i = kIdx; i < n; i++) {
            sum += nums[i] < k ? -1 : 1;
            count += map.getOrDefault(sum - 1, 0);
            count += map.getOrDefault(sum - 2, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        CountSubarraysWithMedianK medianK = new CountSubarraysWithMedianK();
        System.out.println(medianK.countSubarrays(new int[]{3, 2, 1, 4, 5}, 4));
    }

}
