/**
 * @program: suanfa
 * @ClassName: H_21
 * @description: 合并两个有序链表
 * @author: zhoujie07
 * @create: 2026-05-12
 **/
public class H_21 {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }
        return dummy.next;
    }

}
