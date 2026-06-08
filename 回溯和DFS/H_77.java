import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_77
 * @description:
 * @author: zhoujie07
 * @create: 2026-04-28
 **/
public class H_77 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        back(n, k, 1);
        return result;
    }

    public void back(int n, int k, int startIndex) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            back(n, k, i + 1);
            path.removeLast();
        }

    }


    static void main() {
        H_77 solution = new H_77();
        List<List<Integer>> result = solution.combine(4, 2);
        System.out.println(result);
    }
}
