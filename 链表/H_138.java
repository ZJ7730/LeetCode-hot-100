import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_138
 * @description: 138. 随机链表的复制
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
