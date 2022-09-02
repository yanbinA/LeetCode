import java.util.LinkedList;
import java.util.Stack;

/**
 *946. 验证栈序列
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
//        Stack<Integer> list = new Stack<>();
        int[] stack = new int[pushed.length];
        int stackIdx = -1;
        int pop = 0;
        for (int val : pushed) {
            stack[++stackIdx] = val;
            while (stackIdx >= 0 && pop < popped.length && stack[stackIdx] == popped[pop]) {
                pop++;
                stackIdx--;
            }
        }
        return stackIdx == -1;
    }

    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] >= prices[j]) {
                    prices[i] -= prices[j];
                }
            }
        }
        return prices;
    }
}
