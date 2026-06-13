/**
 * @program: suanfa
 * @ClassName: H_160
 * @description: 相交链表
 *
 * 面试笔记：
 * - 题目定位：找两个链表的第一个公共节点。
 * - 核心思路：两个指针分别走完 A+B 和 B+A 的总长度，长度差会被抵消，最终在交点同步相遇。
 * - 状态含义：`pA` 和 `pB` 分别是两个链表上的游标。
 * - 复杂度：时间 O(m+n)，空间 O(1)。
 *
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
