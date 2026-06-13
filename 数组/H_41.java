/**
 * @program: suanfa
 * @ClassName: H_41
 * @description: 41. 缺失的第一个正数
 *
 * 面试笔记：
 * - 题目定位：在数组中找最小的缺失正整数。
 * - 核心思路：如果数字 x 的取值范围在 `[1, n]`，就把它放到下标 `x - 1` 的位置。
 * - 处理方式：循环交换，直到当前位置不是合法数字，或者目标位置已经放对。
 * - 最终判断：遍历一遍后，第一个 `nums[i] != i + 1` 的位置，对应答案就是 `i + 1`。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_41 {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 把数字 x 尽量放回下标 x - 1
                int index = nums[i] - 1;
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
