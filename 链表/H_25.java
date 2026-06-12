/**
 * @program: suanfa
 * @ClassName: H_25
 * @description: 25. K 个一组翻转链表
 * @author: zhoujie07
 * @create: 2026-05-28
 **/
public class H_25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nextNode = head;
        for (int i = 0 ;i <k ;i ++) {
            if (nextNode == null) {
                return head;
            }
            nextNode = nextNode.next;
        }

        ListNode cur = head;
        ListNode pre = null;

        for (int i = 0 ;i <k ;i ++) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        head.next = reverseKGroup(nextNode, k);
        return pre;
    }
}
