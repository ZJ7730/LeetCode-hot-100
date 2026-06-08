/**
 * @program: suanfa
 * @ClassName: H_24
 * @description: 24. 两两交换链表中的节点
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            first.next = second.next;
            second.next = first;
            current.next = second;

            current = first;
        }
        return dummy.next;
    }
}
