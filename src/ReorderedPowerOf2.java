import java.util.Arrays;

/**
 * <h3>重新排序得到 2 的幂</h3>
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 */
public class ReorderedPowerOf2 {
    public static boolean reorderedPowerOf2(int n) {
        String num = String.valueOf(n);
        String[] split = num.split("");
        Arrays.sort(split);
        int power = 1;
        for (int i = 0; i < 31; i++) {
            String str = String.valueOf(power);
            if (str.length() == num.length()) {
                String[] pow = str.split("");
                Arrays.sort(pow);
                if (Arrays.equals(pow, split)) {
                    return true;
                }
            }
            if (str.length() > num.length()) {
                break;
            }
            power *= 2;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(0));
        System.out.println(reorderedPowerOf2(2410));
        System.out.println(reorderedPowerOf2(1));
        System.out.println(reorderedPowerOf2(46));

    }
}
