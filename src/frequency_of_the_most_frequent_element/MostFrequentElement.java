package frequency_of_the_most_frequent_element;

import java.util.Arrays;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 */
public class MostFrequentElement {
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int max=0,tempSum=0;
        for (int i=0,j = 0; j < nums.length; j++) {
            while(nums[j]*(j-i)-tempSum>k){
                tempSum-=nums[i++];
            }
            tempSum+=nums[j];
            max=Math.max(max, j-i+1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxFrequency(new int[]{1, 2, 4}, 5));
        System.out.println(maxFrequency(new int[]{3, 9, 6}, 2));
    }
}
