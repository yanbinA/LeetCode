import java.util.LinkedList;

/**
 * 1089. 复写零
 * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 *
 * 注意：请不要在超过该数组长度的位置写入元素。
 *
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 */
public class DuplicateZeros {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了50.44%的用户
     */
    public void duplicateZeros1(int[] arr) {
        LinkedList<Integer> list = new LinkedList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            list.offer(num);
            if (num == 0) {
                list.offer(num);
            }
            arr[i] = list.poll();
        }
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了35.90%的用户
     */
    public void duplicateZeros2(int[] arr) {
        int n = arr.length;
        int[] list = new int[2 * n];
        int h = 0, t = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            list[t++] = num;
            if (num == 0) {
                list[t++] = num;
            }
            arr[i] = list[h++];
        }
    }
    public void duplicateZeros3(int[] arr) {
        int n = arr.length;
        int[] list = new int[2 * n];
        int h = 0, t = 0;
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            list[t++] = num;
            if (num == 0) {
                list[t++] = num;
            }
            arr[i] = list[h++];
        }
    }
}
