import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @program: suanfa
 * @ClassName: H_20
 * @description: 20. 有效的括号
 *
 * 面试笔记：
 * - 题目定位：判断括号字符串是否合法，要求左右括号类型匹配且顺序正确。
 * - 核心思路：用栈保存“当前还期待出现的右括号”，遇到左括号就压入对应右括号。
 * - 状态含义：栈顶永远是当前最应该匹配的字符。
 * - 复杂度：时间 O(n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-19
 **/
public class H_20 {

    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 看到左括号，就压入它对应的右括号
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
