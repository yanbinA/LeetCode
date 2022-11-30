import java.util.*;

/**
 * 895. 最大频率栈
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack 类:
 *
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 */
public class MaximumFrequencyStack {
    static class FreqStack {
        private int maxCnt;
        private Map<Integer, Integer> cntMap;
        private List[] list;

        public FreqStack() {
            this.list = new ArrayList[20000];
            this.maxCnt = 0;
            this.cntMap = new HashMap<>();
        }

        public void push(int val) {
            int cnt = 0;
            if (cntMap.containsKey(val)) {
                cnt = cntMap.get(val) + 1;
                if (maxCnt < cnt) {
                    maxCnt = cnt;
                }
            }
            cntMap.put(val, cnt);

            if (this.list[cnt] == null) {
                List<Integer> ele = new ArrayList<>();
                this.list[cnt] = ele;
            }
            this.list[cnt].add(val);
        }

        public int pop() {
            List list = this.list[maxCnt];
            int size = list.size();
            if (size == 1) {
                this.list[maxCnt] = null;
                maxCnt--;
            }
            int val = (int) list.remove(size - 1);
            cntMap.put(val, cntMap.get(val) - 1);
            return val;
        }
    }

}
