import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_448
 * @description: 448. 找到所有数组中消失的数字
 *
 * 面试笔记：
 * - 题目定位：数组长度为 n，数字范围在 `[1, n]`，找出所有没有出现过的数字。
 * - 核心思路：数字 x 对应下标 x - 1，用负号标记某个数字是否出现过。
 * - 结果判断：第二次遍历时，仍然是正数的位置，说明对应数字没有出现。
 * - 复杂度：时间 O(n)，空间 O(1) 不计结果。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            // 数字 x 出现过，就把下标 x - 1 的位置标记为负数
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
