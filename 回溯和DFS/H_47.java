import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_47
 * @description: 全排列 II https://leetcode.cn/problems/permutations-ii/description/
 *
 * 面试笔记：
 * - 题目定位：数组中有重复数字，返回所有不重复的排列。
 * - 核心思路：先排序，再在排列模板上做同层去重。
 * - 去重规则：如果当前值和前一个值相同，且前一个值在当前路径里没有被使用，就跳过当前值。
 * - 状态含义：`used[]` 控制元素是否已进入当前排列。
 * - 复杂度：时间 O(n * n!)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-06
 **/
public class H_47 {


    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length]);
        return result;
    }


    public void dfs(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, used);
            used[i] = false;
            path.removeLast();
        }
    }

}
