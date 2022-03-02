import java.util.ArrayList;
import java.util.List;

/**
 * 564. 寻找最近的回文数
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 *
 * “最近的”定义为两个整数差的绝对值最小。
 */
public class FindClosestPalindrome {
    /**
     * 1.用原数的前半部分替换后半部分得到的回文整数。
     * 2.用原数的前半部分加一后的结果替换后半部分得到的回文整数。
     * 3.用原数的前半部分减一后的结果替换后半部分得到的回文整数。
     * 4.为防止位数变化导致构造的回文整数错误，因此直接构造 999…999 和 100…001 作为备选答案。
     * @param n
     * @return
     */
    public String nearestPalindromic(String n) {
        long self = Long.parseLong(n), nearest = -1;
        List<Long> candidates = getCandidates(n);
        for (Long candidate : candidates) {
            if (candidate != self) {
                if (Math.abs(candidate - self) < Math.abs(nearest - self) ||
                        Math.abs(candidate - self) == Math.abs(nearest - self) && candidate < self) {
                    nearest = candidate;
                }
            }
        }
        return String.valueOf(nearest);
    }

    public List<Long> getCandidates(String n) {
        int length = n.length();
        List<Long> candidates = new ArrayList<>();
        candidates.add((long) (Math.pow(10, length) + 1));
        candidates.add((long) (Math.pow(10, length - 1) - 1));
        long prefix = Long.parseLong(n.substring(0, (length + 1) / 2));
        for (long i = prefix - 1; i <= prefix + 1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            StringBuffer suffix = new StringBuffer(sb).reverse();
            sb.append(suffix.substring(length & 1));
            candidates.add(Long.parseLong(sb.toString()));
        }
        return candidates;
    }

    public static void main(String[] args) {
        FindClosestPalindrome palindrome = new FindClosestPalindrome();
        System.out.println(palindrome.nearestPalindromic("10"));
    }
}
