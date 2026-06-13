/**
 * @program: suanfa
 * @ClassName: H_2
 * @description: 两数相加
 *
 * 面试笔记：
 * - 题目定位：两个逆序链表分别表示两个整数，要求按位相加并返回逆序结果链表。
 * - 构造方式：使用 `dummy + tail` 构造结果链表，避免处理头节点特殊情况。
 * - 进位处理：`carry` 保存上一位的进位，循环条件要包含它。
 * - 空节点处理：某一条链表走到头时，把当前位当作 0。
 * - 复杂度：时间 O(max(m, n))，空间 O(max(m, n))。
 *
 * @author: zhoujie07
 * @create: 2026-05-14
 **/
public class H_2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;

            carry = sum / 10;

            int cur = sum % 10;
            // 当前位结果直接接到结果链表尾部
            tail.next = new ListNode(cur);
            tail = tail.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummy.next;
    }

}
