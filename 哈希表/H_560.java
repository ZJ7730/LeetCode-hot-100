import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_560
 * @description: 560. 和为 K 的子数组
 *
 * 核心思路：
 * 1. 定义 preSum 为从 nums[0] 到当前元素的前缀和。
 * 2. 如果存在一个历史前缀和 oldSum，使 preSum - oldSum = k，
 *    那么 oldSum 后面到当前位置这一段子数组的和就是 k。
 * 3. 所以遍历时只需要统计历史前缀和 preSum - k 出现过多少次。
 *
 * 注意：
 * - 数组中可能有负数，不能使用滑动窗口。
 * - map.put(0, 1) 用来处理从下标 0 开始、和正好为 k 的子数组。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_560 {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumCount = new HashMap<>();

        // 前缀和为 0 出现过 1 次
        preSumCount.put(0, 1);

        int result = 0;
        int preSum = 0;

        for (int num : nums) {
            preSum += num;

            // 查找有多少个历史前缀和可以和当前 preSum 组成和为 k 的子数组。
            int need = preSum - k;
            if (preSumCount.containsKey(need)) {
                result += preSumCount.get(need);
            }

            preSumCount.put(preSum, preSumCount.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }
}
