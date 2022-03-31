import java.util.ArrayList;
import java.util.List;

/**
 * 728. 自除数
 * 自除数 是指可以被它包含的每一位数整除的数。
 *
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 *
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 */
public class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        int min = Math.min(9, right);
        int i = left;
        for (; i <= min; i++) {
            ans.add(i);
        }
        for (; i <= right; i++) {
            int curr = i;
            while (curr > 0) {
                if (curr % 10 == 0 || i % (curr % 10) != 0) {
                    break;
                }
                curr /= 10;
            }
            if (curr == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
