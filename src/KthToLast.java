/**
 *
 * Implement an algorithm to find the kth to last element of a singly LL.
 * Created by neilbarooah on 17/09/16.
 */
public class KthToLast {

    private class LinkedListNode<T> {

        T data;
        LinkedListNode<T> next;

        private LinkedListNode(T data, LinkedListNode<T> next) {
            this.data = data;
            this.next = next;
        }

        T getData() {
            return data;
        }

        LinkedListNode<T> getNext() {
            return next;
        }

        void setNext(LinkedListNode<T> next) {
            this.next = next;
        }
    }

    // recursive #1: recurse the LL, and when it hits the end, the method passes back a counter
    // set to 0. Each parent call adds 1 to this counter. When the counter equals k, we know
    // we've reached the kth to the last element of the LL.
    int printKthToLast(LinkedListNode<Integer> head, int k) {
        if (head == null) {
            return 0;
        }
        int index = printKthToLast(head.next, k) + 1;
        if (index == k) {
            System.out.println(k + "th to last node is " + head.data);
        }
        return index;
    }

    // recursive #2: we can't simultaneously return a counter and an index. If we wrap the
    // counter value with simple class, we can mimic passing by reference.
    // takes up O(n) space.
    class Index {
        public int value = 0;
    }

    LinkedListNode kthToLast(LinkedListNode head, int k) {
        Index idx = new Index();
        return kthToLast(head, k, idx);
    }

    LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = kthToLast(head.next, k, idx);
        idx.value = idx.value + 1;
        if (idx.value == k) {
            return head;
        }
        return node;
    }

    // iterative: most optimal solution but less straightforward. We can use 2 pointers
    // and place them k nodes apart in the LL by putting p2 at the beginning and moving p1
    // k nodes into the list. Then we move them at the same pace and p1 will hit the end of
    // the LL after LENGTH - k steps. At this point, p2 is k nodes from the end.
    // this takes O(n) time and O(n) space.
    LinkedListNode nthToLastEfficient(LinkedListNode head, int k) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        // move p1 k nodes into the list
        for (int i = 0; i < k; i++) {
            if (p1 == null) {
                return null; // out of bounds
            }
            p1 = p1.next;
        }

        // move p1 and p2 at the same pace.
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

}
