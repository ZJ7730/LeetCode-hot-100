import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_77
 * @description: 77. 组合
 *
 * 面试笔记：
 * - 题目定位：从 `1..n` 中选出 `k` 个数。
 * - 核心思路：组合问题使用 `startIndex` 控制每层从哪里开始枚举，避免重复选择之前的元素。
 * - 状态含义：`path` 保存当前已选数字，`path.size() == k` 时收集结果。
 * - 复杂度：时间 O(k * C(n,k))，空间 O(k)。
 *
 * @author: zhoujie07
 * @create: 2026-04-28
 **/
public class H_77 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        back(n, k, 1);
        return result;
    }

    public void back(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            back(n, k, i + 1);
            path.removeLast();
        }

    }


    static void main() {
        H_77 solution = new H_77();
        List<List<Integer>> result = solution.combine(4, 2);
        System.out.println(result);
    }
}
