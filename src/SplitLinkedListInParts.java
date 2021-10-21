import common.ListNode;

/**
 *给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 *
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 *
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 *
 * 返回一个由上述 k 部分组成的数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
 */
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] listNodes = new ListNode[k];
        ListNode curr = head;
        int size = 0;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        int avg = size / k;
        int remain = size % k;
        curr = head;
        for (int i = 0; i < k; i++) {
            int partSize = avg + (remain-- > 0 ? 1 : 0);
            ListNode temp = curr;
            listNodes[i] = temp;
            if (curr != null) {
                curr = temp.next;
                while (--partSize > 0) {
                    temp = curr;
                    curr = temp.next;
                }
                temp.next = null;
            }
        }
        return listNodes;
    }

}
