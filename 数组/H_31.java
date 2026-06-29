/**
 * @program: suanfa
 * @ClassName: H_31
 * @description: 31. 下一个排列
 *
 * 面试笔记：
 * - 题目定位：在原数组上找出字典序中“刚好比当前排列大”的下一个排列。
 * - 核心思路：从右往左找第一个可以变大的位置 i，也就是满足 nums[i] < nums[i + 1] 的位置。
 * - 交换规则：再从右往左找第一个严格大于 nums[i] 的数 nums[j]，交换 nums[i] 和 nums[j]。
 * - 收尾规则：交换后，i 右侧仍然是降序，需要反转成升序，保证后缀尽可能小。
 * - 边界情况：如果找不到 i，说明整个数组是降序，已经是最大排列，直接反转成最小排列。
 * - 面试易错点：比较时要跳过相等值，所以判断条件使用 >=，否则会把相等数字拿来交换，导致结果没有真正变大。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_31 {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;

        // 1. 从右往左找第一个能变大的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = len - 1;

            // 2. 从右往左找第一个严格大于 nums[i] 的数
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }

            swap(nums, i, j);
        }

        // 3. 把后缀从降序反转成升序，得到刚好更大的排列
        reverse(nums, i + 1, len - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        H_31 h_31 = new H_31();
        int[] nums = {1, 1};
        h_31.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

}
