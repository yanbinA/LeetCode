package evaluate_reverse_polish_notation;

import java.util.LinkedList;

/**
 * 根据 逆波兰表示法，求表达式的值。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 */
public class EvalRPN {
    public int evalRPN1(String[] tokens) {
        LinkedList<Integer> list = new LinkedList<>();
        int num1;
        int num2;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    num1 = list.pollLast();
                    num2 = list.pollLast();
                    list.add(num1 + num2);
                    break;
                case "-":
                    num1 = list.pollLast();
                    num2 = list.pollLast();
                    list.add(num2 - num1);
                    break;
                case "*":
                    num1 = list.pollLast();
                    num2 = list.pollLast();
                    list.add(num1 * num2);
                    break;
                case "/":
                    num1 = list.pollLast();
                    num2 = list.pollLast();
                    list.add(num2 / num1);
                    break;
                default:
                    list.add(Integer.parseInt(token));
            }
        }
        return list.pollLast();
    }

    public int evalRPN(String[] tokens) {
        int[] total = new int[tokens.length / 2 + 1];

        int num1, num2, left = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    num1 = total[--left];
                    num2 = total[--left];
                    total[left++] = num1 + num2;
                    break;
                case "-":
                    num1 = total[--left];
                    num2 = total[--left];
                    total[left++] = num2 - num1;
                    break;
                case "*":
                    num1 = total[--left];
                    num2 = total[--left];
                    total[left++] = num1 * num2;
                    break;
                case "/":
                    num1 = total[--left];
                    num2 = total[--left];
                    total[left++] = num2 / num1;
                    break;
                default:
                    total[left++] = Integer.parseInt(token);
            }
        }
        return total[0];
    }
}
