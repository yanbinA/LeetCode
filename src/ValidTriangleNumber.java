import java.util.Arrays;

/**
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 */
public class ValidTriangleNumber {
    public static int triangleNumber1(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int right = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            int left = right = i + 1;
            for (; left < nums.length - 1; left++) {
                int middle = nums[left];
                right = right == left ? right + 1 : right;
                for (; right < nums.length; right++) {
                    if (nums[right] >= first + middle) {
                        break;
                    }
                }
                count += right - left - 1;
            }
        }
        return count;
    }

    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int[] counts = new int[1001];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int middle = nums[j];
                count += counts[Math.min(middle + first - 1, 1000)] - counts[middle - first];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(triangleNumber(new int[]{2, 2, 3, 4}));
        System.out.println(triangleNumber(new int[]{4, 2, 3, 4}));
    }
}
