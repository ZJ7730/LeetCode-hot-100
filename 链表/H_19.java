/**
 * @program: suanfa
 * @ClassName: H_19
 * @description: 删除倒数第N个节点
 * @author: zhoujie07
 * @create: 2026-05-12
 **/
public class H_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

}
