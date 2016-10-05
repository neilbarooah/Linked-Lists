import java.util.LinkedList;

/**
 * You have 2 numbers represented by a LL, where each node contains a single digit. These
 * digits are stored in reverse order, such that 1's digit is at the head of the list.
 * Write a function that adds the 2 numbers and returns the sum as a linked list.
 *
 * Do the same for digits stored in the forward order.
 * Created by neilbarooah on 17/09/16.
 */
public class SumLists {

    LinkedListNode<Integer> addLists(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        LinkedListNode<Integer> result = new LinkedListNode<Integer>(null, null, null);
        int value = carry;
        if (l1 != null) {
            value += l1.data;
        }
        if (l2 != null) {
            value += l2.data;
        }
        result.data = value % 10;

        if (l1 != null || l2!= null) {
            LinkedListNode<Integer> more = addLists(l1 == null ? null : l1.next,
                    l2 == null ? null : l2.next, value >= 10 ? 1 : 0);
            result.setNext(more);
        }
        return result;
    }

    // for linked lists that stores the digits in the forward order, we may have LLs of
    // different sizes so we must first compare the lengths and pad the smaller one with
    // zeros
    class PartialSum {
        public LinkedListNode<Integer> sum = null;
        public int carry = 0;
    }

    LinkedListNode<Integer> addLists(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
        int len1 = length(l1);
        int len2 = length(l2);

        if (len1 < len2) {
            l2 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }

        PartialSum sum = addListsHelper(l1, l2);

        // if there was a carry value left over, insert this at the front of the list.
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            LinkedListNode<Integer> result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

    int length(LinkedListNode<Integer> l) {
        int total = 0;
        while (l != null) {
            total += 1;
            l = l.next;
        }
        return total;
    }

    PartialSum addListsHelper(LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        // add smaller digits recursively
        PartialSum sum = addListsHelper(l1.next, l2.next);

        // add carry to current data
        int val = sum.carry + l1.data + l2.data;

        // insert sum of the current digits
        LinkedListNode full_result = insertBefore(sum.sum, val % 10);

        // return sum so far, and the carry value
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    // pad the list with zeros
    LinkedListNode padList(LinkedListNode<Integer> l, int padding) {
        LinkedListNode<Integer> head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    // helper function to insert node in the front of the linked list.
    LinkedListNode<Integer> insertBefore(LinkedListNode<Integer> list, int data) {
        LinkedListNode<Integer> node = new LinkedListNode<Integer>(data, null, null);
        if (list != null) {
            node.next = list;
        }
        return node;
    }
}
