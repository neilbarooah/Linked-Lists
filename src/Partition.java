/**
 * Partition a LL around a value x, such that all nodes less than x come before all nodes
 * greater than or equal to x. If x is contained within te list, the values of x only need
 * to be after the elements less than x. The partition element x can appear anywhere in the
 * "right partition"; it doesn't need to appear between the left and right partitions.
 *
 * Created by neilbarooah on 17/09/16.
 */
public class Partition {

    // create 2 LL: one more items smaller than x and other for items greater than or equal
    // x. At the end, merge the 2 lists. This is a stable approach in that elements stay in
    // their original order, other than the necessary movement around the partition.
    LinkedListNode partition(LinkedListNode<Integer> node, int x) {
        LinkedListNode<Integer> beforeStart = null;
        LinkedListNode<Integer> beforeEnd = null;
        LinkedListNode<Integer> afterStart = null;
        LinkedListNode<Integer> afterEnd = null;

        while (node != null) {
            if (node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            // need to clear the original nodes as we go
            LinkedListNode<Integer> next = node.next;
            node.next = null;
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        } else if (afterStart == null) {
            return beforeStart;
        }

        beforeEnd.next = afterStart;
        return beforeStart;
    }

    // for cleaner code but not quite "stable" in the positions of the elements, we can
    // rearrange the elements by growing the list at the head and tail.
    LinkedListNode cleanPartition(LinkedListNode<Integer> node, int x) {
        LinkedListNode<Integer> head = node;
        LinkedListNode<Integer> tail = node;

        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return head;
    }
 }
