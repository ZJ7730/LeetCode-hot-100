/**
 * @program: suanfa
 * @ClassName: H_92
 * @description: 92. 反转链表 II
 *
 * 面试笔记：
 * - 题目定位：只反转链表中第 `left` 到第 `right` 个节点。
 * - 核心思路：先定位区间前驱 `pre` 和区间起点 `tail`，再用头插法把区间内节点一个个插到 `pre` 后面。
 * - 状态含义：`pre` 始终指向反转区间前一个节点；`tail` 始终是反转后区间的尾节点。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
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

        for (int i = left ; i < right; i++) {
            ListNode move = tail.next;
            // 每次把 tail 后面的节点摘下来，插到 pre 后面
            tail.next = move.next;
            move.next = pre.next;
            pre.next = move;
        }

        return dummy.next;
    }




}
