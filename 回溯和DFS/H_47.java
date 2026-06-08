import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_47
 * @description: 全排列 II https://leetcode.cn/problems/permutations-ii/description/
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
