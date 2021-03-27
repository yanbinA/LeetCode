package rotate_list;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int len = 1;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            len++;
        }
        temp.next = head;
        k = len - k % len;
        for (int i = 0; i < k - 1; i++) {
            head = head.next;
        }
        ListNode newNode = head.next;
        head.next = null;
        return newNode;
    }
}
