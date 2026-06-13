/**
 * @program: suanfa
 * @ClassName: H_19
 * @description: 删除倒数第N个节点
 *
 * 面试笔记：
 * - 题目定位：删除链表倒数第 n 个节点，重点是要安全处理删除头节点的情况。
 * - 核心技巧：加入 `dummy` 后，删除头节点也能统一为“删除某个节点的后继”。
 * - 指针关系：`fast` 先走 `n + 1` 步，保证 `slow` 最终停在待删除节点前一个位置。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-12
 **/
public class H_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next != null) {
            // 直接跳过待删除节点
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

}
