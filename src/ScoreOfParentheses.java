import java.util.Stack;

/**
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(0);
                continue;
            }
            int pop1 = stack.pop();
            if (pop1 == 0) {
                stack.push(1);
                continue;
            }
            int sum = pop1;
            int pop3 = stack.pop();
            while (pop3 != 0) {
                sum += pop3 ;
                pop3 = stack.pop();
            }
            stack.push(sum * 2);
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        ScoreOfParentheses parentheses = new ScoreOfParentheses();
        System.out.println(parentheses.scoreOfParentheses("(()(()))"));
    }
}
