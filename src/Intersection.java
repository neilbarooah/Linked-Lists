import java.util.LinkedList;

/**
 * Given two singly linked lists, determine is the 2 lists intersect. Return the intersecting
 * node. Note that the intersection is defined based on reference, not value. That is, if
 * the kth node of the first linked list if the exact same node as the jth node of the second
 * linked list, they are intersecting.
 *
 * start at the tail and chop off the excess of the longer LL from the front
 *
 * Created by neilbarooah on 17/09/16.
 */
public class Intersection {

    public class Result {
        public LinkedListNode tail;
        public int size;
        public Result(LinkedListNode tail, int size) {
            this.tail = tail;
            this.size = size;
        }
    }

    // this takes O(A + B) time where A and B are the lengths of the LL. It takes O(1) space.
    LinkedListNode findIntersection(LinkedListNode list1, LinkedListNode list2) {
        if (list1 == null || list2 == null) {
            return null;
        }

        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        if (result1.tail != result2.tail) {
            return null;
        }

        // set pointers to the start of each linked list
        LinkedListNode shorter = result1.size < result2.size ? list1 : list2;
        LinkedListNode longer = result1.size < result2.size ? list2 : list2;

        // advance the pointer for the longer LL by difference in lengths
        longer = getKthNode(longer, Math.abs(result1.size - result2.size));

        // move both pointers until you have a collision
        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }
        return longer;
    }

    Result getTailAndSize(LinkedListNode list) {
        if (list == null) {
            return new Result(null, 0);
        }
        int size = 1;
        LinkedListNode current = list;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return new Result(current, size);
    }

    LinkedListNode getKthNode(LinkedListNode head, int k) {
        LinkedListNode current = head;
        while (k > 0 && current != null) {
            current = current.next;
            k--;
        }
        return current;
    }
}
