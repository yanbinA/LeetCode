import common.ListNode;

/**
 * 817. 链表组件
 * 给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表 nums，该列表是上述链表中整型值的一个子集。
 *
 * 返回列表 nums 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 nums 中）构成的集合。
 */
public class LinkedListComponents {
    public int numComponents(ListNode head, int[] nums) {
        boolean[] bs = new boolean[10001];
        for (int num : nums) {
            bs[num] = true;
        }
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            if (bs[curr.val] && (curr.next == null || !bs[curr.next.val])) {
                count++;
            }
            curr = curr.next;
        }
        return count;
    }
}
