import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: suanfa
 * @ClassName: H_155
 * @description: 155. 最小栈
 *
 * 面试笔记：
 * - 题目定位：设计一个栈，除了 `push/pop/top` 还要能 O(1) 返回当前最小值。
 * - 核心思路：维护两个栈，一个存数据，一个同步存当前阶段的最小值。
 * - 状态含义：`stack` 存所有元素，`minStack` 的栈顶始终是当前最小值。
 * - 复杂度：所有操作都为 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-20
 **/
public class H_155 {

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public H_155() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            // 新元素更小或相等时，最小栈同步入栈
            minStack.push(val);
        }

    }

    public void pop() {
        Integer num = stack.pop();
        if (num.equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}
