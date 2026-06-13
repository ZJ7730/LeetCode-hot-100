/**
 * @program: suanfa
 * @ClassName: H_141
 * @description: 环形链表
 *
 * 面试笔记：
 * - 题目定位：判断链表是否有环。
 * - 核心思路：快慢指针，快指针每次走两步，慢指针每次走一步；如果有环，快慢指针最终会相遇。
 * - 状态含义：`fast` 和 `slow` 分别表示快慢指针当前位置。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_141 {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }

        }
        return false;
    }

}
