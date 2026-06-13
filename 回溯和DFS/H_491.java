import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: suanfa
 * @ClassName: H_491
 * @description: 递增子序列 http://leetcode.cn/problems/non-decreasing-subsequences/description/
 *
 * 面试笔记：
 * - 题目定位：找长度至少为 2 的非递减子序列，要求保持原数组顺序。
 * - 核心思路：不能排序，因此每一层递归单独用 `Set` 去重，避免同层重复分支。
 * - 状态含义：`path` 保存当前子序列；`startIndex` 控制下一次从哪里继续选。
 * - 约束规则：如果当前数字小于路径最后一个数字，就不能选，因为会破坏非递减性质。
 * - 复杂度：时间 O(n * 2^n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-05
 **/
public class H_491 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();


    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums, 0);
        return result;
    }


    public void dfs(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }

        Set<Integer> uset = new HashSet<>();

        for (int i = startIndex; i < nums.length; i++) {
            if ((!path.isEmpty() && nums[i] < path.getLast()) || uset.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            uset.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
        }
    }

    static void main(String[] args) {
        H_491 solution = new H_491();
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> result = solution.findSubsequences(nums);
        System.out.println(result);
    }



}
