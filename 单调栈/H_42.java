/**
 * @program: suanfa
 * @ClassName: H_42
 * @description: 42. 接雨水
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_42 {

    /**
     * 计算每个位置能接多少雨水
     *
     * <p>解题思路：双指针对撞 + 维护两侧最大高度。
     *
     * <p>核心思想：每个位置 i 能接的雨水量 = min(leftMax, rightMax) - height[i]，
     * 其中 leftMax 是 i 左侧（含）的最大高度，rightMax 是 i 右侧（含）的最大高度。
     * 谁是短板，雨水就由谁决定。
     *
     * <p>关键观察：用 left、right 双指针从两端往中间逼近，同时维护 leftMax 和 rightMax。
     * 当 leftMax <= rightMax 时，对于 left 位置来说，右侧已知有一个 >= leftMax 的高度（rightMax），
     * 所以 left 位置的短板一定是 leftMax，可以直接结算 leftMax - height[left]，无需关心右侧具体地形；
     * 反之当 leftMax > rightMax 时，right 位置的短板一定是 rightMax，结算 rightMax - height[right]。
     *
     * <p>每一步只移动短板一侧的指针，因为短板侧的雨水量已确定，无需再访问。
     *
     * @param height 柱子高度数组
     * @return 总共能接的雨水量
     * @author zhoujie07
     * @date 2026/05/26
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        int res = 0;

        // 双指针对撞，谁矮谁结算
        while (left <= right) {
            // 同步更新两侧的历史最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax <= rightMax) {
                // 左边是短板，left 位置能接的雨水由 leftMax 决定，左指针右移
                res += leftMax - height[left];
                left++;
            } else if (leftMax > rightMax) {
                // 右边是短板，right 位置能接的雨水由 rightMax 决定，右指针左移
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
