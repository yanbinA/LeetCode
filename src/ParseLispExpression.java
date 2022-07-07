import java.util.HashMap;
import java.util.Map;

/**
 *736. Lisp 语法解析
 * 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 *
 * 表达式语法如下所示:
 *
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 * let 表达式采用 "(let v1 e1 v2 e2 ... vn en expr)" 的形式，其中 let 总是以字符串 "let"来表示，接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量 v1被分配为表达式 e1 的值，第二个变量 v2 被分配为表达式 e2 的值，依次类推；最终 let 表达式的值为 expr表达式的值。
 * add 表达式表示为 "(add e1 e2)" ，其中 add 总是以字符串 "add" 来表示，该表达式总是包含两个表达式 e1、e2 ，最终结果是 e1 表达式的值与 e2 表达式的值之 和 。
 * mult 表达式表示为 "(mult e1 e2)" ，其中 mult 总是以字符串 "mult" 表示，该表达式总是包含两个表达式 e1、e2，最终结果是 e1 表达式的值与 e2 表达式的值之 积 。
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 */
public class ParseLispExpression {
    public int evaluate(String expression) {
        char[] chars = expression.toCharArray();
        Expression exp = new Expression(0, null);
        return exp.evaluate(chars);
    }

    static class Expression {
        private final Expression parent;
        private int idx;
        private final Map<String, Integer> params;

        public Expression(int idx, Expression parent) {
            this.idx = idx;
            this.parent = parent;
            this.params = new HashMap<>();
        }

        int value(String v) {
            char[] chars = v.toCharArray();
            int value = 0, i = 0, neg = 1;
            if (chars[i] == '-') {
                neg = -1;
                i++;
            } else if (chars[i] == '+') {
                i++;
            }
            for (; i < chars.length; i++) {
                if (!Character.isDigit(chars[i])) {
                    return this.get(v);
                }
                value = value * 10 + chars[i] - '0';
            }
            return value * neg;
        }

        int get(String k) {
            if (params.containsKey(k)) {
                return params.get(k);
            }
            return parent.get(k);
        }

        int evaluate(char[] chars) {
            if (chars[idx] == '(') {
                idx++;
            }
            StringBuilder sb = new StringBuilder();
            while (idx < chars.length && (chars[idx] != ' ' && chars[idx] != ')')) {
                sb.append(chars[idx++]);
            }
            String start = sb.toString();
            if ("let".equals(start)) {
                int ans = 0;
                StringBuilder v = new StringBuilder();
                while (chars[idx] != ')') {
                    idx++;
                    if (chars[idx] == '(') {
                        Expression e1 = new Expression(idx, this);
                        ans = e1.evaluate(chars);
                        break;
                    }
                    v = new StringBuilder();
                    while (chars[idx] != ' ' && chars[idx] != ')') {
                        v.append(chars[idx++]);
                    }
                    if (chars[idx] == ')') {
                        ans = value(v.toString());
                    } else {
                        Expression e1 = new Expression(++idx, this);
                        int val = e1.evaluate(chars);
                        params.put(v.toString(), val);
                    }

                }
                this.updateParentIdx(idx + 1);
                return ans;

            }
            if ("add".equals(start)) {
                Expression e1 = new Expression(idx + 1, this);
                int v1 = e1.evaluate(chars);
                Expression e2 = new Expression(idx + 1, this);
                int v2 = e2.evaluate(chars);
                updateParentIdx(idx + 1);
                return v1 + v2;
            }
            if ("mult".equals(start)) {
                Expression e1 = new Expression(idx + 1, this);
                int v1 = e1.evaluate(chars);
                Expression e2 = new Expression(idx + 1, this);
                int v2 = e2.evaluate(chars);
                updateParentIdx(idx + 1);
                return v1 * v2;
            }
            updateParentIdx(idx);
            return this.value(start);
        }
        public void updateParentIdx(int idx) {
            if (this.parent != null) {
                parent.idx = idx;
            }
        }
    }



    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put(new String("x"), 2);
        System.out.println(map.get("x"));

        ParseLispExpression expression = new ParseLispExpression();
        System.out.println(expression.evaluate("(add 2 3)"));
        System.out.println(expression.evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
        System.out.println(expression.evaluate("(add (add 1 (mult 2 2)) (add 3 4))"));
        System.out.println(expression.evaluate("4"));
    }
}
