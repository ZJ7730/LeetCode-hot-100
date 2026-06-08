/**
 * @program: suanfa
 * @ClassName: ListNode
 * @description: 链表题通用单链表节点
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class ListNode {

    /**
     * 节点存储的整数值。
     */
    int val;

    /**
     * 指向下一个链表节点的引用。
     */
    ListNode next;

    /**
     * @author zhoujie07
     * @description 创建默认链表节点
     * @date 2026-05-26
     */
    ListNode() {
    }

    /**
     * @param val 节点值
     * @author zhoujie07
     * @description 根据节点值创建链表节点
     * @date 2026-05-26
     */
    ListNode(int val) {
        this.val = val;
    }

    /**
     * @param val 节点值
     * @param next 下一个链表节点
     * @author zhoujie07
     * @description 根据节点值和后继节点创建链表节点
     * @date 2026-05-26
     */
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
