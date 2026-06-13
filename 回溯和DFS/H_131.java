import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_131
 * @description: 131. 分割回文串
 *
 * 面试笔记：
 * - 题目定位：把字符串切分成若干段，每一段都必须是回文串。
 * - 核心思路：回溯枚举切分位置；每次尝试从 `start` 到 `end` 的所有子串，只有回文才继续往下搜。
 * - 状态含义：`path` 保存当前切分结果，`start` 表示下一段切分的起点。
 * - 收集条件：当 `start == s.length()` 时，说明整个字符串已被成功切分。
 * - 复杂度：和切分方案数量相关，回文判断会影响单次扩展成本。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_131 {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (!isPalindrome(s, start, end)) {
                continue;
            }
            path.add(s.substring(start, end + 1));
            backtrack(s, end + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
