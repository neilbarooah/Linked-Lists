import java.util.LinkedList;
import java.util.Stack;

/**
 * Implement a function to check if a LL is a palindrome
 * Created by neilbarooah on 17/09/16.
 */
public class Palindrome {

    // #1: reverse the LL and compare the reversed list to the original.
    boolean isPalindrome(LinkedListNode head) {
        LinkedListNode reversed = reverseAndClone(head);
        return isEqual(head, reversed);
    }

    LinkedListNode reverseAndClone(LinkedListNode node) {
        LinkedListNode head = null;
        while (node != null) {
            LinkedListNode n = new LinkedListNode(node.data, null, null);
            n.next = head;
            head = n;
            node = node.next;
        }
        return head;
    }

    boolean isEqual(LinkedListNode one, LinkedListNode two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one == null && two == null;
    }

    // Iterative approach: store first half of the LL into a stack by using the fast-slow
    // runner approach. Then, iterate through the rest of the LL and compare the node to
    // the top of the stack.
    boolean isPalindromIterative(LinkedListNode<Integer> head) {
        LinkedListNode<Integer> fast = head;
        LinkedListNode<Integer> slow = head;

        Stack<Integer> stack = new Stack<Integer>();
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        // check for odd number of elements
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            int top = stack.pop().intValue();
            if (top != slow.data) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    // Recursive approach:
    boolean isPalindromeRecursive(LinkedListNode head) {
        int length = lengthOfList(head);
        Result p = isPalindromeRecurse(head, length);
        return p.result;
    }

    Result isPalindromeRecurse(LinkedListNode head, int length) {
        if (head == null || length <= 0) { // even number of nodes
            return new Result(head, true);
        } else if (length == 1) { // odd number of nodes
            return new Result(head.next, true);
        }

        Result res = isPalindromeRecurse(head.next, length - 2);

        // if child calls are not a palindrome, pass back up a failure
        if (!res.result || res.node == null) {
            return res;
        }

        // check if matches corresponding node on other side
        res.result = (head.data == res.node.data);

        // return corresponding node
        res.node = res.node.next;
        return res;
    }

    int lengthOfList(LinkedListNode n) {
        int size = 0;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }

    private class Result {

        boolean result;
        LinkedListNode node;

        private Result(LinkedListNode node, boolean result) {
            this.result = result;
            this.node = node;
        }
    }
}
