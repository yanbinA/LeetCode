package remove_duplicates;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        return dfs(head);
    }

    private ListNode dfs(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next != null && head.next.val == head.val) {
            head = head.next;
            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            return dfs(head.next);
        }
        head.next = dfs(head.next);
        return head;
    }
}
