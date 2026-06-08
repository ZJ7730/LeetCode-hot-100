import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: suanfa
 * @ClassName: H_491
 * @description: 递增子序列 http://leetcode.cn/problems/non-decreasing-subsequences/description/
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
