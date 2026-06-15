/**
 * @program: suanfa
 * @ClassName: H_581
 * @description: 581. 最短无序连续子数组
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_581_ {

    /**
     * 寻找最短无序连续子数组
     *
     * <p>解题思路：两次遍历，分别确定无序子数组的右边界和左边界。
     *
     * <p>核心思想：如果一个数组完全有序，那么从左往右每个元素都 >= 前面的最大值，
     * 从右往左每个元素都 <= 后面的最小值。违反这个规则的位置就是无序子数组的边界。
     *
     * <p>第一遍遍历（从左往右）：维护已遍历部分的最大值 max，
     * 若 nums[i] < max，说明 i 位置破坏了升序，i 属于无序子数组，更新右边界 right = i；
     * 否则更新 max。遍历结束后 right 就是无序子数组的最远右边界。
     *
     * <p>第二遍遍历（从右往左）：维护已遍历部分的最小值 min，
     * 若 nums[i] > min，说明 i 位置破坏了降序（从右往左看应递减），i 属于无序子数组，更新左边界 left = i；
     * 否则更新 min。遍历结束后 left 就是无序子数组的最远左边界。
     *
     * <p>若 right == -1，说明数组已经有序，返回 0；否则返回 right - left + 1。
     *
     * @param nums 整数数组
     * @return 最短无序连续子数组的长度
     * @author zhoujie07
     * @date 2026/05/26
     */
    public int findUnsortedSubarray(int[] nums) {
        int length = nums.length;

        int max = nums[0];
        int min = nums[length - 1];

        int left = -1;
        int right = -1;

        // 从左往右：找无序子数组的右边界
        // 如果 nums[i] < max，说明 i 处的值比前面出现过的最大值还小，位置 i 需要被排序
        for (int i = 0; i < length; i++) {
            if (nums[i] < max) {
                right = i;
            } else {
                max = nums[i];
            }
        }

        // 从右往左：找无序子数组的左边界
        // 如果 nums[i] > min，说明 i 处的值比后面出现过的最小值还大，位置 i 需要被排序
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            } else {
                min = nums[i];
            }
        }

        // right == -1 说明数组已有序
        return right == -1 ? 0 : right - left + 1;
    }

}
