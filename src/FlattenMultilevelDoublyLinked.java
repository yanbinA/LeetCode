import java.util.LinkedList;

/**
 * 扁平化多级双向链表
 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 *
 * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list
 */
public class FlattenMultilevelDoublyLinked {
    public static Node flatten(Node head) {
        flattenChild(head);
        return head;
    }

    private static Node flattenChild(Node child) {
        Node curr = child;
        while (curr != null) {
            Node next = curr.next;
            if (curr.child != null) {
                Node last = flattenChild(curr.child);
                curr.child.prev = curr;
                curr.next = curr.child;
                curr.child = null;
                last.next = next;
                if (next == null) {
                    return last;
                }
                next.prev = last;
            } else {
                if (next == null) {
                    return curr;
                }
            }
            curr = next;
        }
        return curr;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node(){}
        public Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
//[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11= new Node(11);
        Node node12 = new Node(12);
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        node3.next = node4;
        node3.child = node7;
        node4.prev = node3;
        node4.next = node5;
        node5.prev = node4;
        node5.next = node6;
        node6.prev = node5;
        node6.next = null;
        node7.prev = null;
        node7.next = node8;
        node8.prev = node7;
        node8.next = node9;
        node8.child = node11;
        node9.prev = node8;
        node9.next = node10;
        node10.prev = node9;
        node10.next = null;
        node11.prev = null;
        node11.next = node12;
        node12.prev = node11;
        node12.next = null;
        Node flatten = flatten(node1);
        System.out.println(flatten);
    }
}
