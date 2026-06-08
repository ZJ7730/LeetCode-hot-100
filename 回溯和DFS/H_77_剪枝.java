import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_77
 * @description:
 * @author: zhoujie07
 * @create: 2026-04-28
 **/
public class H_77_剪枝 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        back(n, k, 1);
        return result;
    }



    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    public void back(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            back(n, k, i + 1);
            path.removeLast();
        }

    }


    static void main() {
        H_77_剪枝 solution = new H_77_剪枝();
        List<List<Integer>> result = solution.combine(4, 2);
        System.out.println(result);
    }
}
