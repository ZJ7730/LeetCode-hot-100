/**
 * @program: suanfa
 * @ClassName: H_160
 * @description: 相交链表
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }


        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            if (pA == null) {
                pA = headB;
            } else {
                pA = pA.next;
            }

            if (pB == null) {
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }

}
