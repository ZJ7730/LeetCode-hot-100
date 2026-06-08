/**
 * @program: suanfa
 * @ClassName: H_142
 * @description: 环形链表II
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_142 {


    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode start = head;
        ListNode end = null;

        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                end = slow;
                break;
            }
        }



        while (end != null && start != end) {
            start = start.next;
            end = end.next;
        }

        return end;
    }





}
