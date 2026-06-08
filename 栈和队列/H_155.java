import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: suanfa
 * @ClassName: H_155
 * @description: 155. 最小栈
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
