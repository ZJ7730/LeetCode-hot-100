/**
 * @program: suanfa
 * @ClassName: H_141
 * @description: 环形链表
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_141 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;
    }

}
