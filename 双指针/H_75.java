/**
 * @program: suanfa
 * @ClassName: H_75
 * @description: 75. 颜色分类
 *
 * 面试笔记：
 * - 题目定位：只包含 0、1、2 的数组原地排序。
 * - 指针含义：
 *   1. `low` 之前全部是 0。
 *   2. `[low, mid)` 全部是 1。
 *   3. `(high, n - 1]` 全部是 2。
 *   4. `mid` 负责扫描未知区域。
 * - 迁移规则：
 *   1. `nums[mid] == 0`，交换到前面，`low++`、`mid++`。
 *   2. `nums[mid] == 1`，当前值已经在中间区域，`mid++`。
 *   3. `nums[mid] == 2`，交换到后面，`high--`，`mid` 不能动。
 * - 为什么 `mid` 不能动：换过来的值还没检查，必须留在原地继续处理。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_75 {

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // 0 放到前面区域
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // 1 保持在中间区域
                mid++;
            } else {
                // 2 放到后面区域，mid 不动，继续检查交换过来的值
                int temp = nums[high];
                nums[high] = nums[mid];
                nums[mid] = temp;
                high--;
            }
        }
    }
}
