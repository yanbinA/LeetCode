/**
 * 396. 旋转函数
 * 给定一个长度为 n 的整数数组 nums 。
 *
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 *
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 *
 * 生成的测试用例让答案符合 32 位 整数。
 */
public class RotateFunction {

    //F(0) = 0 * arr[0] + 1 * arr[1] + ... + (n - 1) * arr[n - 1]
    //F(1) = 0 * arr[1] + ... + (n - 2) * arr[n - 1] + (n - 1) * arr[0]
    //      = F(0) - sum(1..n-1) +  (n-1)* arr[0]
    //      = F(0) - sum +  (n) * arr[0]
    //F(2) = 0 * arr[2] + ... + (n - 2) * arr[n - 1] + (n - 1) * arr[1]
    //      = F(2) - sum(0,2..n-1) +  (n-1)* arr[1]
    //      = F(2) - sum +  (n) * arr[1]
    //      = F(0) - sum + n * arr[0] - sum(0..n-1) + n * arr[1]
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0, previous = 0;
        for (int j = 0; j < n; j++) {
            previous += j * nums[j];
            sum += nums[j];
        }
        int max = previous;
        for (int rotate = 1; rotate < n; rotate++) {
            previous += -sum + n * nums[rotate - 1];
            max = Math.max(max, previous);
        }
        return max;
    }




    public static void main(String[] args) {
        RotateFunction function = new RotateFunction();
        System.out.println(function.maxRotateFunction(new int[]{4, 3, 2, 6}));
    }
}
