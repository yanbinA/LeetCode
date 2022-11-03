import java.util.Arrays;

/**
 * 902. 最大为 N 的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 *
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 */
public class NumbersAtMostNGivenDigitSet {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int length = digits.length;
        int nl = String.valueOf(n).length();
        int ans = 0;
        int[] mults = new int[nl];
        int[] counts = new int[nl];
        mults[0] = 1;
        for (int i = 1; i < nl; i++) {
            mults[i] = mults[i - 1] * length;
        }
        Arrays.fill(counts, length);
        counts[nl - 1] = 0;
        //计算digits的区间个数
        int[] ds = new int[10];
        for (String digit : digits) {
            ds[Integer.parseInt(digit)] = 1;
        }
        for (int i = 1; i < ds.length; i++) {
            ds[i] += ds[i - 1];
        }
        char[] chars = String.valueOf(n).toCharArray();
        for (int i = 0; i < nl; i++) {
            int idx = chars[i] - '0';
            counts[nl - i - 1] += ds[idx];
            if (idx == 0 || ds[idx] == ds[idx - 1]) {
                break;
            }
            if (i != nl - 1) {
                counts[nl - i - 1]--;
            }
        }
        for (int i = 0; i < nl; i++) {
            ans += counts[i] * mults[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        NumbersAtMostNGivenDigitSet digitSet = new NumbersAtMostNGivenDigitSet();
        System.out.println(digitSet.atMostNGivenDigitSet(new String[]{"1", "3", "5","7"}, 313));
    }
}
