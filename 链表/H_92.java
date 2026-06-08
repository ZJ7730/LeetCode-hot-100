/**
 * @program: suanfa
 * @ClassName: H_92
 * @description:
 * @author: zhoujie07
 * @create: 2026-05-14
 **/
public class H_92 {


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode tail = head;

        for (int i = 1; i< left; i++) {
            pre = pre.next;
            tail = tail.next;
        }


        /*
         * 当前结构：
         *
         * pre -> tail -> ...
         * pre -> tail -> move -> ...
         */

        for (int i = left ; i < right; i++) {
            ListNode move = tail.next;
            tail.next = move.next;
            move.next = pre.next;
            pre.next = move;
        }

        return dummy.next;
    }




}
