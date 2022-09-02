/**
 * 640. 求解方程
 * 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 *
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 *
 * 如果方程中只有一个解，要保证返回值 'x' 是一个整数。
 */
public class SolveTheEquation {
    public String solveEquation(String equation) {
        int xc = 0, sum = 0;
        int op = 1, left = 1;
        char[] chars = equation.toCharArray();
        int n = equation.length();
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                int num = (c - '0') * op;
                while (++i < n) {
                    char cur = chars[i];
                    if (Character.isDigit(cur)) {
                        num = num * 10 + (cur - '0') * op;
                        continue;
                    }
                    if (cur == 'x') {
                        xc += num;
                        num = 0;
                        break;
                    }
                    switch (cur) {
                        case '=' -> {
                            left = -1;
                            op = left;
                        }
                        case '-' -> op = -left;
                        case '+' -> op = left;
                    }
                    break;
                }
                sum += num;
                continue;
            }
            switch (c) {
                case '=' -> {
                    left = -1;
                    op = left;
                }
                case '-' -> op = -left;
                case '+' -> op = left;
                case 'x' -> xc += op;
            }
        }
        if (xc == 0 && sum != 0) {
            return "No solution";
        }
        if (xc == 0) {
            return "Infinite solutions";
        }
        int ans = (-sum) / xc;
        return "x=" + ans;
    }
}
