import java.util.PriorityQueue;

/**
 * @program: suanfa
 * @ClassName: H_215
 * @description: 215. 数组中的第K个最大元素
 * <p>
 * 核心思路：
 * 1. 维护一个大小为 k 的小顶堆。
 * 2. 堆中始终保存当前遍历过的最大 k 个元素。
 * 3. 当堆大小超过 k 时，弹出堆顶最小值。
 * 4. 遍历结束后，堆顶就是第 k 个最大元素。
 * <p>
 * 为什么用小顶堆：
 * - 只关心最大的 k 个数，不需要完整排序。
 * - 小顶堆堆顶是这 k 个数里最小的，也就是第 k 大。
 * <p>
 * 时间复杂度：O(n log k)
 * 空间复杂度：O(k)
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_215 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

}
