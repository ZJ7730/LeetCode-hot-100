import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_138
 * @description: 138. 随机链表的复制
 *
 * 面试笔记：
 * - 题目定位：复制一个带 `random` 指针的链表，要求 next 和 random 都要完整复刻。
 * - 当前做法：先用哈希表把“原节点 -> 新节点”的映射建好，再统一补 next 和 random。
 * - 核心技巧：第二遍遍历时，直接通过 `map.get(...)` 找到对应的新节点。
 * - 复杂度：时间 O(n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_138 {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }

        current = head;
        while (current != null) {
            Node copy = map.get(current);
            // next/random 都指向映射表中的复制节点
            copy.next = map.get(current.next);
            copy.random = map.get(current.random);
            current = current.next;
        }
        return map.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
