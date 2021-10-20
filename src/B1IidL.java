import java.util.HashMap;
import java.util.Random;

/**
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 *
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/B1IidL
 */
public class B1IidL {

    public int peakIndexInMountainArray(int[] arr) {
        new HashMap<>(1);
        int left = 0,right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public int peakIndexInMountainArray1(int[] arr) {
        int left = 0,right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == 0) {
                if (arr[mid] > arr[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                    continue;
                }
            }
            if (mid == arr.length - 1) {
                if (arr[mid] > arr[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                    continue;
                }
            }
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
