public class MyLinkedList {
    static class Node {
        public int value;
        public Node next;
        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node head;
    Node tail;
    int length;

    public MyLinkedList() {
        head = new Node(-2, null);
        tail = head;
        length = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= length) {
            return -1;
        }
        Node cur = head.next;
        int count = 0;
        while (cur != null) {
            if (index == count) {
                return cur.value;
            }
            cur = cur.next;
            count++;
        }
        return -1;
    }

    public void addAtHead(int val) {
        Node node = new Node(val, head.next);
        head.next = node;
        if (length == 0) {
            tail = node;
        }
        length++;
    }

    public void addAtTail(int val) {
        tail.next = new Node(val, null);
        tail = tail.next;
        length++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > length) {
            return;
        }
        if (index == 0) {
            this.addAtHead(val);
            return;
        }
        if (index == length) {
            this.addAtTail(val);
            return;
        }
        Node cur = head;
        int count = 0;
        while (cur != null) {
            if (index == count) {
                cur.next = new Node(val, cur.next);
                length++;
                return;
            }
            cur = cur.next;
            count++;
        }

    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }

        Node cur = head;
        int count = 0;
        while (cur != null) {
            if (index == count) {
                cur.next = cur.next.next;
                if (cur.next == null) {
                    tail = cur;
                }
                length--;
                return;
            }
            cur = cur.next;
            count++;
        }
    }
}
