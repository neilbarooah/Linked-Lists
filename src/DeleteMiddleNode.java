/**
 * Implement an algorithm to delete a node in the middle of a singly LL, given only access
 * to that node
 * Created by neilbarooah on 17/09/16.
 */
public class DeleteMiddleNode {

    boolean deleteNode(LinkedListNode n) {
        if (n == null || n.getNext() == null) {
            return false;
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }
}
