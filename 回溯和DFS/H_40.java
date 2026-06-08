import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_40
 * @description: 组合总和II
 * @author: zhoujie07
 * @create: 2026-04-30
 **/
public class H_40 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] userd = new int[candidates.length];
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, 0, userd);
        return result;
    }

    public void backtrack(int[] candidates, int target, int sum, int startIndex, int[] userd) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && userd[i - 1] == 0) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            userd[i] = 1;
            backtrack(candidates, target, sum, i + 1, userd);
            path.removeLast();
            sum -= candidates[i];
            userd[i] = 0;

        }


    }

    static void main(String[] args) {
        H_40 solution = new H_40();
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = solution.combinationSum2(candidates, target);
        System.out.println(result);
    }

}
