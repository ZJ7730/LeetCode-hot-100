import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_39
 * @description: 组合总和
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
