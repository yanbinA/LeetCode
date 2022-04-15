/**
 *357. 统计各位数字都不同的数字个数
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        int count = 0;
        if (n >= 0) {
            count += 1;
        }
        if (n >= 1) {
            count += 9;
        }
        int cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            count += cur;
        }
        return count;
    }
    //91 9 * 9 * 8
    //91 648
    //739
    public static void main(String[] args) {
        CountNumbersWithUniqueDigits digits = new CountNumbersWithUniqueDigits();
        System.out.println(digits.countNumbersWithUniqueDigits(3));
    }
}
