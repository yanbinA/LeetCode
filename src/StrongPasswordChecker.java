import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 420. 强密码检验器
 *
 *
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 *
 * 在一步修改操作中，你可以：
 *
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 */
public class StrongPasswordChecker {
    public int strongPasswordChecker(String password) {
        int nc = 0, lc = 0, uc = 0;
        PriorityQueue<Integer> list = new PriorityQueue<>((a, b) -> a % 3 == b % 3 ? a - b : (a % 3 - b % 3));
        char[] chars = password.toCharArray();
        int n = password.length();
        char c = '-';
        int count = 0;
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                nc++;
            }
            if (aChar >= 'a' && aChar <= 'z') {
                lc++;
            }
            if (aChar >= 'A' && aChar <= 'Z') {
                uc++;
            }
            if (aChar == c) {
                count++;
            } else {
                if (count >= 3) {
                    list.add(count);
                }
                count = 1;
                c = aChar;
            }
        }
        if (count >= 3) {
            list.add(count);
        }
        int dc = (nc == 0 ? 1 : 0) + (lc == 0 ? 1 : 0) + (uc == 0 ? 1 : 0);
        int operation = 0;
        if (n < 6) {
            int dl = 6 - n;
            while (dl > 0) {
                operation++;
                //新增操作
                dl--;
                //新增一个没有的元素
                dc--;
                //新增在连续相同的元素之间
                if (list.peek() != null) {
                    int i = list.poll() - 2;
                    if (i >= 3) {
                        list.add(i);
                    }
                }
            }
        }
        if (n > 20) {
            int dl = n - 20;
            while (dl > 0) {
                operation++;
                //删除操作
                dl--;
                //删除在连续相同的元素之间
                if (list.peek() != null) {
                    int i = list.poll() - 1;
                    if (i >= 3) {
                        list.add(i);
                    }
                }
            }
        }
        while (dc > 0) {
            operation++;
            //替换一个没有的元素
            dc--;
            //替换在连续相同的元素之间
            if (list.peek() != null) {
                int i = list.poll() - 3;
                if (i >= 3) {
                    list.add(i);
                }
            }
        }
        while (!list.isEmpty()) {
            int len = list.poll();
            operation += len / 3;
        }
        return operation;
    }

    public static void main(String[] args) {
        StrongPasswordChecker checker = new StrongPasswordChecker();
        System.out.println(checker.strongPasswordChecker("111aaa"));
        System.out.println(checker.strongPasswordChecker("bbaaaaaaaaaaaaaaacccccc"));
    }
}
