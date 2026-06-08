import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @program: suanfa
 * @ClassName: H_20
 * @description: 20. 有效的括号
 * @author: zhoujie07
 * @create: 2026-05-19
 **/
public class H_20 {

    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();

    }

    public static void main(String[] args) {
        H_20 h_20 = new H_20();
        System.out.println(h_20.isValid("()[]{}"));
    }
}
