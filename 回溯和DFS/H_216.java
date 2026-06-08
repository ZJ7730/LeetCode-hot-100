import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_216
 * @description: 组合总和III
 * @author: zhoujie07
 * @create: 2026-04-29
 **/
public class H_216 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();


    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n, 0, 1);
        return result;
    }


    public void backtracking(int k, int n, int sum, int startIndex) {
        if (sum > n) {
            return;
        }
        if (path.size() == k && sum == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            sum += i;
            backtracking(k, n, sum, i + 1);
            sum -= i;
            path.removeLast();
        }
    }

    static void main(String[] args) {
        H_216 solution = new H_216();
        int k = 3;
        int n = 7;
        List<List<Integer>> result = solution.combinationSum3(k, n);
        System.out.println(result);
    }

}
