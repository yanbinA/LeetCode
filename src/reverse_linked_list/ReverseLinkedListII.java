package reverse_linked_list;

import common.ListNode;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode top = new ListNode(0), pre = top,curr = head;
        top.next = head;
        int index = 1;
        while (head.next != null && index < right) {
            if (index >= left && index < right) {
                ListNode temp = head.next.next;
                head.next.next = curr;
                curr = head.next;
                head.next = temp;
            } else if (index < left){
                pre = head;
                curr = head.next;
                head = head.next;
            }
            index++;
        }
        pre.next = curr;
        return top.next;
    }
}
