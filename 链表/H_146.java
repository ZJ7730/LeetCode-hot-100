import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_146
 * @description: 146. LRU 缓存
 *
 * 核心思路：
 * 1. HashMap 负责 O(1) 根据 key 找到链表节点。
 * 2. 双向链表负责 O(1) 删除节点、把节点移动到头部、淘汰尾部节点。
 * 3. 头部表示最近使用，尾部表示最久未使用。
 *
 * 操作规则：
 * - get 命中：返回 value，并把节点移动到头部。
 * - put 已存在：更新 value，并把节点移动到头部。
 * - put 不存在且容量满：删除 tail.prev，也就是最久未使用节点。
 *
 * 时间复杂度：get/put 都是 O(1)
 * 空间复杂度：O(capacity)
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_146 {

    static class Node {
        int k, v;

        Node prev, next;

        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;

    public H_146(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);

        // 使用虚拟头尾节点，统一处理插入和删除边界。
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 从双向链表中删除一个真实节点。
     */
    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 把节点插入到头部，表示它最近被使用过。
     */
    public void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }


    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        // 被访问后升级为最近使用。
        remove(node);
        addToHead(node);
        return node.v;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.v = value;
            remove(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                // tail.prev 是最久未使用节点。
                Node leastRecentlyUsed = tail.prev;
                remove(leastRecentlyUsed);
                map.remove(leastRecentlyUsed.k);
            }

            Node node = new Node(key, value);
            addToHead(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
