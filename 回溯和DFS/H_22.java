import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_22
 * @description: 括号生成 https://leetcode.cn/problems/generate-parentheses/description/
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
