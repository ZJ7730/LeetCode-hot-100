/**
 * @program: suanfa
 * @ClassName: H_169
 * @description: 169. 多数元素
 *
 * 面试笔记：
 * - 题目定位：找出数组中出现次数超过 `n / 2` 的元素。
 * - 核心思路：摩尔投票，维护一个候选值 `target` 和票数 `count`。
 * - 抵消规则：遇到相同元素就加票，遇到不同元素就减票；当票数归零时，重新选择当前元素作为候选。
 * - 为什么正确：多数元素的出现次数大于其他所有元素之和，成对抵消后，最后留下的候选一定是它。
 * - 复杂度：时间 `O(n)`，空间 `O(1)`。
 *
 * <p>
 * 核心思路：
 * 1. 用 `target` 记录当前候选多数元素。
 * 2. 用 `count` 记录该候选的“票数”。
 * 3. 遍历数组时，相同元素就给候选加票，不同元素就抵消一票。
 * 4. 当 `count == 0` 时，说明前面的候选已经被抵消完，直接把当前元素作为新候选。
 * <p>
 * 为什么用摩尔投票：
 * - 这题只要求返回出现次数超过一半的元素，不需要统计每个元素的精确次数。
 * - 多数元素与其他元素两两抵消后，最终剩下的就是答案。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_169 {

    public int majorityElement(int[] nums) {
        int target = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                // 票数归零，重新选择当前元素作为候选
                target = nums[i];
                count = 1;
            } else if (nums[i] == target) {
                // 和候选相同，票数加一
                count++;
            } else {
                // 和候选不同，进行一次抵消
                count--;
            }
        }

        return target;
    }

}
