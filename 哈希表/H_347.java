import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @program: suanfa
 * @ClassName: H_347
 * @description: 347. 前 K 个高频元素
 *
 * 面试笔记：
 * - 题目定位：返回数组中出现频率最高的 k 个元素，不要求结果顺序。
 * - 核心思路：先统计频率，再用大小为 k 的小顶堆保留频率最高的 k 个元素。
 * - 堆语义：堆里存 `[数字, 频率]`，堆顶始终是当前保留集合里频率最低的元素。
 * - 复杂度：时间 O(n log k)，空间 O(n)。
 *
 * <p>
 * 核心思路：
 * 1. 先用 HashMap 统计每个数字出现的频率。
 * 2. 再维护一个大小为 k 的小顶堆，堆按照频率从小到大排序。
 * 3. 当堆大小超过 k 时，弹出频率最低的元素。
 * 4. 遍历结束后，堆中剩下的就是出现频率最高的 k 个元素。
 * <p>
 * 时间复杂度：O(n log k)，n 是数组长度
 * 空间复杂度：O(n)，频率表最多保存 n 个不同数字
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_347 {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        countMap.forEach((num, count) -> {
            minHeap.offer(new int[]{num, count});
            if (minHeap.size() > k) {
                // 只保留频率最高的 k 个元素
                minHeap.poll();
            }
        });

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll()[0];
        }

        return result;
    }
}
