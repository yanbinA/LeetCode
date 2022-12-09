/**
 * 1775. 通过最少操作次数使数组的和相等
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 *
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 *
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 */
public class EqualSumArraysWithMinimumNumberOfOperations {
    public int minOperations(int[] nums1, int[] nums2) {
        int[] cnt = new int[6];
        int diff = 0;
        for (int num : nums1) {
            diff += num;
            cnt[num - 1]++;
        }
        for (int num : nums2) {
            diff -= num;
            cnt[6 - num]++;
        }
        if (diff < 0) {
            for (int i = 0; i < 3; i++) {
                int tem = cnt[i];
                cnt[i] = cnt[5 - i];
                cnt[5 - i] = tem;
            }
            diff = -diff;
        }
        int operation = 0;
        for (int i = 5; i > 0; i--) {
            if (i * cnt[i] >= diff) {
                operation += (diff + i - 1) / i;
                return operation;
            }
            operation += cnt[i];
            diff -= cnt[i] * i;
        }
        if (diff != 0) {
            return -1;
        }
        return operation;
    }

    public static void main(String[] args) {
        EqualSumArraysWithMinimumNumberOfOperations numberOfOperations = new EqualSumArraysWithMinimumNumberOfOperations();
        System.out.println(numberOfOperations.minOperations(new int[]{3,3,2,4,2,6,2}, new int[]{6,2,6,6,1,1,4,6,4,6,2,5,4,2,1}));
        System.out.println(numberOfOperations.minOperations(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 1, 2, 2, 2, 2}));
        System.out.println(numberOfOperations.minOperations(new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{6}));
        System.out.println(numberOfOperations.minOperations(new int[]{6,6}, new int[]{1}));
        //[3,3,2,4,2,6,2]                   22
        //[6,2,6,6,1,1,4,6,4,6,2,5,4,2,1]   56
        //34->5->9->3
    }
}
