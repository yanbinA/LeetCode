import java.net.MalformedURLException;

/**
 * <h3>367. 有效的完全平方数</h3>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int square = num / mid;
            if (num % mid == 0 && square == mid) {
                return true;
            }
            if (mid > square) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
