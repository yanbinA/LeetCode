import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 */
public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int length = arr.length;
        int left = 0,right = length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] -x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> ans = new ArrayList<>(k);
        for (int i = left; i < k + left; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }
}
