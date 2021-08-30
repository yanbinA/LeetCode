import java.util.Arrays;
import java.util.Random;

/**
 *  按权重随机选择
 * 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，
 * 它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。
 *
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 *
 * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode-cn.com/problems/random-pick-with-weight"></a>
 */
public class RandomPickWithWeight {

    private final int[] weightTable;
    private final int weight;
    private final Random random;

    public RandomPickWithWeight(int[] w) {
        this.weightTable = new int[w.length];
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            weightTable[i] = sum;
        }
        this.random = new Random();
        this.weight = sum;
    }

    /**
     * 线性查找->二分查找
     * @return
     */
    public int pickIndex() {
        int target = this.random.nextInt(this.weight) + 1;
        int left = 0, right = this.weightTable.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (this.weightTable[mid] > target) {
                right = mid;
            } else if (this.weightTable[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
    //3,14,1,7
    //3,17,18,25

//    public RandomPickWithWeight(int[] w) {
//        int sum = 0;
//        for (int i : w) {
//            sum += i;
//        }
//        this.weightTable = new int[sum];
//        int left = 0;
//        for (int i = 0; i < w.length; i++) {
//            Arrays.fill(this.weightTable, left, left + w[i], i);
//            left += w[i];
//        }
//        this.random = new Random();
//        this.weight = sum;
//    }
//
//    public int pickIndex() {
//
//        int i = this.random.nextInt(this.weight);
//        return this.weightTable[i];
//    }

    public static void main(String[] args) {
        RandomPickWithWeight weight = new RandomPickWithWeight(new int[]{1, 3});
        int size = 10000;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += weight.pickIndex();
        }
        System.out.println(sum + "/" + size);

        int[] ints = {3, 17, 18, 25};

        label:for (int target = 1; target <= 25; target++) {
            int left = 0, right = ints.length - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (ints[mid] > target) {
                    right = mid;
                } else if (ints[mid] < target) {
                    left = mid + 1;
                } else {
                    System.out.printf("target:%d>>mid:%d\n", target, mid);
                    continue label;
                }
            }
            System.out.printf("target:%d>>left:%d>>right:%d\n", target, left, right);
        }

    }

}
