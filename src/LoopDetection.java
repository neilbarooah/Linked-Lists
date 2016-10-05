/**
 * Given a circular LL, implement an algorithm that returns the node at the beginning
 * of the loop.
 *
 * 1. create 2 pointers FastPointer and SlowPointer
 * 2. Move FastPointer at a rate of 2 steps and SlowPointer at a rate of 1 step.
 * 3. When they collide, move SlowPointer to LinkedListHead. Keep FastPointer where it is.
 * 4. Move SlowPointer and FastPointer at a rate of one step. Return the new collision point
 * Created by neilbarooah on 18/09/16.
 */
public class LoopDetection {

    LinkedListNode FindBeginning(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        // find meeting point, which will be loop size - k steps into LL
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // error check - no meeting point means no loop
        if (fast == null || fast.next == null) {
            return null;
        }

        // move slow to Head. Keep fast at meeting point. Each are k steps from the start
        // of the loop. If they move at the same pace, they must meet at loop start.
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // both now point to start of the loop
        return fast;
    }
}
