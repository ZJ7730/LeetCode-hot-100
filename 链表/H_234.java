/**
 * @program: suanfa
 * @ClassName: H_234
 * @description: 回文链表
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_234 {


    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }


        ListNode reverse = reverse(slow);

        ListNode cur = head;
        ListNode cur1 = reverse;

        while (cur1 != null) {
            if (cur.val != cur1.val) {
                return false;
            }
            cur = cur.next;
            cur1 = cur1.next;
        }

        return true;


    }


    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

}
