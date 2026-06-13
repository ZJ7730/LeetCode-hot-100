import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_39
 * @description: 组合总和
 *
 * 面试笔记：
 * - 题目定位：候选数字可以重复使用，找出所有和为 target 的组合。
 * - 核心思路：组合问题使用 `startIndex` 控制枚举起点，允许重复使用时下一层继续传当前下标。
 * - 状态含义：`sum` 表示当前路径的总和，`path` 保存已选数字。
 * - 剪枝规则：`sum > target` 直接返回，避免无效分支继续深入。
 * - 复杂度：和结果规模相关，空间与递归深度相关。
 *
 * @author: zhoujie07
 * @create: 2026-04-29
 **/
public class H_39 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, target, 0, 0);
        return result;
    }

    public void backtracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(candidates, target, sum, i);
            sum -= candidates[i];
            path.removeLast();
        }

    }

    static void main(String[] args) {
        H_39 solution = new H_39();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = solution.combinationSum(candidates, target);
        System.out.println(result);
    }

}
