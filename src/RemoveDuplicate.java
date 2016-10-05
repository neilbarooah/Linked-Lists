import java.util.*;
/**
 *
 * Write code to remove duplicates from an unsorted linked list. How would you solve this
 * if a temporary buffer is not allowed
 *
 * Created by neilbarooah on 17/09/16.
 */
public class RemoveDuplicate {

    // To remove duplicates, we need to be able to track the duplicates. We'll use a simple
    // hash table to do that. This takes O(n) time
    void DeleteDups(LinkedListNode n) {
        HashSet<Integer> set = new HashSet<Integer>();
        LinkedListNode previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    private class LinkedListNode<Integer> {

        int data;
        LinkedListNode<Integer> next;

        private LinkedListNode(int data, LinkedListNode<Integer> next) {
            this.data = data;
            this.next = next;
        }

        int getData() {
            return data;
        }

        LinkedListNode<Integer> getNext() {
            return next;
        }

        void setNext(LinkedListNode<Integer> next) {
            this.next = next;
        }
    }

    // if we don't have a buffer, we can iterate with 2 pointers: current which iterates
    // through the LL and runner which checks all subsequent nodes for duplicates.
    // This takes O(1) space but O(n^2) time.
    void deleteDuplicates(LinkedListNode head) {
        LinkedListNode current = head;
        while (current != null) {
            // remove all future nodes that have the same value
            LinkedListNode runner = current;
            while (runner.next != null) {
                if (runner.next.data == current.data) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }
}
