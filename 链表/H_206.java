/**
 * @program: suanfa
 * @ClassName: H_206
 * @description: 反转链表
 * @author: zhoujie07
 * @create: 2026-05-12
 **/
public class H_206 {


    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur!=null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        return dfs(head, null);
    }

    public ListNode dfs(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        return dfs(tmp, cur);
    }





}
