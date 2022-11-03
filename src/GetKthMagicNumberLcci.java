import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 面试题 17.09. 第 k 个数
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 */
public class GetKthMagicNumberLcci {
    public int getKthMagicNumber(int k) {
        int[] arr = new int[k];
        arr[0] = 1;
        int p3 = 0;
        int p5 = 0;
        int p7 = 0;
        for (int i = 1; i < k; i++) {
            int val = Math.min(arr[p3] * 3, Math.min(arr[p5] * 5, arr[p7] * 7));
            if (val % 3 == 0) {
                p3++;
            }
            if (val % 5 == 0) {
                p5++;
            }
            if (val % 7 == 0) {
                p7++;
            }
            arr[i] = val;
        }
        return arr[k - 1];
    }
    //1,3,5,7,9,15,21,25,27,35,45,

    public static void main(String[] args) {
        GetKthMagicNumberLcci lcci = new GetKthMagicNumberLcci();
        for (int i = 0; i < 100; i++) {
            System.out.println(lcci.getKthMagicNumber(i + 1));
        }

    }

}
