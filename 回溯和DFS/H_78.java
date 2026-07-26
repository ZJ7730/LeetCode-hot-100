import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_78
 * @description: 78. 子集
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_78 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        back(nums, 0);
        return result;
    }

    public void back(int[] nums, int startIndex) {
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            back(nums, i + 1);
            path.removeLast();
        }
    }
}
