/**
 * @program: suanfa
 * @ClassName: H_2
 * @description: 两数相加
 * @author: zhoujie07
 * @create: 2026-05-14
 **/
public class H_2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        int carry = 0;
        while (l1 != null || l2 != null || carry !=0) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;

            carry = sum / 10;

            int cur = sum % 10;
            tail.next = new ListNode(cur);
            tail = tail.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummy.next;
    }

}
