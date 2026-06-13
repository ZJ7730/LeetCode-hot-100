/**
 * @program: suanfa
 * @ClassName: H_153
 * @description: 153. 寻找旋转排序数组中的最小值
 *
 * 面试笔记：
 * - 题目定位：在一个升序数组旋转后的结果中，找最小值。
 * - 核心思路：通过比较 `nums[mid]` 和 `nums[right]` 判断最小值在左半段还是右半段。
 * - 迁移规则：
 *   1. 如果 `nums[mid] > nums[right]`，说明最小值一定在右半段。
 *   2. 否则最小值在左半段，包含 `mid`。
 * - 复杂度：时间 O(log n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_153 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                // 最小值在右半段
                left = mid + 1;
            } else {
                // 最小值在左半段，mid 也可能是答案
                right = mid;
            }
        }
        return nums[left];
    }
}
