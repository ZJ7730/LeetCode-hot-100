import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_448
 * @description: 448. 找到所有数组中消失的数字
 *
 * 核心思路：
 * 1. 题目给定 nums 中的数字范围是 [1, n]，可以把数字 x 映射到下标 x - 1。
 * 2. 遍历数组时，把出现过的数字对应位置标记为负数。
 * 3. 第二次遍历时，如果某个位置仍然是正数，说明下标 i 对应的数字 i + 1 没出现过。
 *
 * 注意：
 * - 标记时要使用 Math.abs(nums[i])，因为当前位置可能已经被前面的数字标记成负数。
 * - 这是原地标记法，会修改输入数组。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)，不计返回结果
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            // 数字 x 出现过，就把下标 x - 1 的位置标记为负数。
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

}
