/**
 * @program: suanfa
 * @ClassName: H_45
 * @description: 45. 跳跃游戏 II
 *
 * 面试笔记：
 * - 题目定位：从数组起点跳到终点，求最少跳跃次数。
 * - 核心思路：把每一次跳跃看成一层，`end` 表示当前这一步能覆盖到的最远边界，`farthest` 表示下一步能到的最远位置。
 * - 计数规则：只有当遍历到当前层边界 `i == end` 时，才真正增加一次跳跃。
 * - 为什么可行：在当前覆盖区间内不断更新下一层的最远可达位置，等于一次性选择当前层最优落点。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_45 {

    public int jump(int[] nums) {
        int steps = 0;
        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                // 走到当前层边界时，再进入下一层
                steps++;
                end = farthest;
            }
        }
        return steps;
    }
}
