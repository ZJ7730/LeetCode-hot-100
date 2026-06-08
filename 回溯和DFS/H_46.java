import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_46
 * @description: 全排列 https://leetcode.cn/problems/permutations/description/
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
