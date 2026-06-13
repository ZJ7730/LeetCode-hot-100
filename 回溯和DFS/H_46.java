import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_46
 * @description: 全排列 https://leetcode.cn/problems/permutations/description/
 *
 * 面试笔记：
 * - 题目定位：返回数组的所有排列。
 * - 核心思路：排列问题没有 `startIndex`，每一层都从头扫描所有元素；用 `used[]` 表示是否已经在当前路径中使用。
 * - 状态含义：`path` 是当前排列，`used[i]` 表示下标 i 是否已经进入路径。
 * - 结束条件：当路径长度等于数组长度时，得到一个完整排列。
 * - 复杂度：时间 O(n * n!)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-05
 **/
public class H_46 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();


    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, new boolean[nums.length]);
        return result;
    }

    public void dfs(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i< nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, used);
            used[i] = false;
            path.removeLast();
        }
    }

    static void main(String[] args) {
        H_46 solution = new H_46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.permute(nums);
        System.out.println(result);
    }


}
