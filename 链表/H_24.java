/**
 * @program: suanfa
 * @ClassName: H_24
 * @description: 24. 两两交换链表中的节点
 *
 * 面试笔记：
 * - 题目定位：每两个节点一组进行交换，保持其余结构不变。
 * - 核心技巧：使用 `dummy` 统一处理头节点；`current` 始终指向待交换区间的前驱。
 * - 交换顺序：先断开 `first` 和 `second`，再把 `second` 接到前面，最后把 `first` 接到 `second` 后面。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_24 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            // 先处理指针关系，再整体向后移动
            first.next = second.next;
            second.next = first;
            current.next = second;

            current = first;
        }
        return dummy.next;
    }
}
