/**
 * @program: suanfa
 * @ClassName: H_142
 * @description: 环形链表II
 *
 * 面试笔记：
 * - 题目定位：返回链表中环的入口节点。
 * - 核心思路：先用快慢指针找相遇点，再让一个指针从头节点出发，另一个从相遇点出发，同步前进，第一次相遇就是入口。
 * - 状态含义：`start` 从头出发，`end` 从相遇点出发；`end == null` 表示无环。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-13
 **/
public class H_142 {


    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode start = head;
        ListNode end = null;

        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                end = slow;
                break;
            }
        }

        while (end != null && start != end) {
            start = start.next;
            end = end.next;
        }

        return end;
    }





}
