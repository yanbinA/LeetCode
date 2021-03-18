package reverse_linked_list;

/**
 * 反转一个单链表(简单)
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *  输入: 1->2->3->4->5->NULL
 *  输出: 5->4->3->2->1->NULL
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        return dfs(null, head, head.next);
    }

    private ListNode dfs(ListNode first,ListNode before, ListNode current) {
        if (current == null) {
            return before;
        }
        ListNode temp = current.next;
        current.next = before;
        before.next = first;
        return dfs(before, current, temp);
    }
}
