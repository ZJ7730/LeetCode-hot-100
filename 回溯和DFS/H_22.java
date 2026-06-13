import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_22
 * @description: 括号生成 https://leetcode.cn/problems/generate-parentheses/description/
 *
 * 面试笔记：
 * - 题目定位：生成所有合法的 n 对括号。
 * - 核心思路：递归过程中记录左括号和右括号已经放了多少个，只允许构造前缀合法的路径。
 * - 状态含义：`left` 表示已放左括号数量，`right` 表示已放右括号数量。
 * - 约束规则：`left < n` 时可以放左括号，`right < left` 时才可以放右括号。
 * - 复杂度：时间与合法结果数量相关，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-06
 **/
public class H_22 {

    List<String> result = new ArrayList<>();
    List<String> path = new ArrayList<>();


    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n);
        return result;
    }


    public void dfs(int left, int right, int n) {
        if (path.size() == 2 * n) {
            result.add(String.join("", path.toArray(new String[0])));
            return;
        }


        if (left < n) {
            path.add("(");
            dfs(left + 1, right, n);
            path.removeLast();
        }

        if (right < left) {
            path.add(")");
            dfs(left, right + 1, n);
            path.removeLast();
        }


    }




}
