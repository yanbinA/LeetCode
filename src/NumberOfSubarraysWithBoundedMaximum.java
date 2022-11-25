/**
 * 795. 区间子数组个数
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 *
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 */
public class NumberOfSubarraysWithBoundedMaximum {
    //假设nums最大元素在[left, right]内
    //从左往右遍历
    //找打nums[i]在[left,right]范围内nums[i]的作用 i * (n - i - 1) + n
    //继续找到nums[j] j * (n - j - 1) + n - {i * (n -j - 1) + i + (n-j-1) + 1}
    //                  = j*(n-j-1) + n - i*(n-j-1)-i-n+j
    //                  =j*(n-j-1) - i*(n-j-1)-i+j
    //                  =j*(n-j) - i*(n-j)
    // 总数 i * (n - i - 1) + n + j*(n-j) - i*(n-j)
    //     =i*(n-i-1)+n+j*(n-j) - i*(n-j)
    //     =i*(j-i-1)+n+j*(n-j)
    //继续找打nums[k] k * (n - k - 1) + n - {j * (n -k - 1) + j + (n-k-1) + 1}
    //                  =k*(n-k) - j*(n-k)
    // 总数 i*(j-i-1)+n+j*(n-j) + k*(n-k) - j*(n-k)
    //     =i*(j-i-1)+n+j*(k-j) + k*(n-k)
    //     =-i + i*(j-i) + j*(k-j) + k*(n-k) + n
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int sum = 0;
        int l0 = -1,l1 = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num >= left) {
                l1 = i;
                if (num > right) {
                    l0 = i;
                }
            }
            sum += l1 - l0;
        }

        return sum;
    }

    public int numSubarrayBoundedMax1(int[] nums, int left, int right) {
        boolean sub = false;
        int sum = 0;
        int before = 0;
        int start = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > right) {
                if (sub) {
                    sum += (before - start)*(i - before) + i;
                    sub = false;
                }
                start = i+1;
            }
            if (num <= right && num >= left) {
                if (!sub) {
                    sum -= i;
                    sub = true;
                } else {
                    sum += (before - start)*(i - before);
                }
                before = i;
            }
        }
        if (sub) {
            sum += (before - start)*(n - before) + n;
        }
        return sum;
    }

    public static void main(String[] args) {
        NumberOfSubarraysWithBoundedMaximum maximum = new NumberOfSubarraysWithBoundedMaximum();
        // -2 + 2*3 + 5*3+8+9
        System.out.println(maximum.numSubarrayBoundedMax(new int[]{1, 1, 3, 2, 2, 4, 1, 1, 5}, 3, 5));
        System.out.println(maximum.numSubarrayBoundedMax(new int[]{2,1,4,3}, 2, 3));
        System.out.println(maximum.numSubarrayBoundedMax(new int[]{2,9,2,5,6}, 2, 8));
    }
}

// 1 1 3 2 2 4 1 1 5
//i=2  2*6 +9          21
//i=5  5*3+9 - (2*3+2+3+1)  24-12=12
//i=8  8*0+9 -(5*0+5+0+1)  9-6=3
//36
//2*2+9+5*3+8*1=36

// 2 3 1 [2,3]
// 5
// 0+0+2+1
// 1+1+1+1
// 0+0+1+1
// 2 3 1 [1,3]
//6
// 2的影响 0+0+2+1
// 3的影响 1+1+1+1
// 3的负影响(重复的) 0+0+1+1
// 1的影响 0+2+0+1
// 1的负 0+1+0+1

