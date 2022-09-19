import java.util.Arrays;

/**
 * 670. 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 */
public class MaximumSwap {
    // nums 987654
    // max  123455

    public int maximumSwap(int num) {
        int n = 9;
        int[] nums = new int[n];
        int[] max = new int[n];
        int r = n - 1;
        max[n - 1] = r;
        int tem = num;
        nums[n - 1] = tem % 10;
        r--;
        tem /= 10;
        while (tem > 0) {
            nums[r] = tem % 10;
            tem /= 10;
            if (nums[max[r + 1]] <= nums[r + 1]) {
                max[r] = r + 1;
            } else {
                max[r] = max[r + 1];
            }
            r--;
        }
        int ans = num;
        for (int i = r + 1; i < n; i++) {
            if (nums[max[i]] > nums[i]) {
                int diff = nums[max[i]] - nums[i];
                ans += Math.pow(10, n - i - 1) * diff - Math.pow(10, n - max[i] - 1) * diff;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumSwap swap = new MaximumSwap();
        System.out.println(swap.maximumSwap(2736));
    }

    public double trimMean(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int trim = n / 20;
        double sum = 0;
        for (int i = trim; i < n - trim; i++) {
            sum += arr[i];
        }
        return sum / (n - trim - trim);

    }
}
