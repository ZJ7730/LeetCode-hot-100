import java.util.PriorityQueue;

/**
 * @program: suanfa
 * @ClassName: H_295
 * @description: 295. 数据流的中位数
 *
 * 面试笔记：
 * - 题目定位：支持持续插入数字，并随时返回当前中位数。
 * - 核心思路：用两个堆维护左右两半数据，左边是大顶堆，右边是小顶堆。
 * - 状态含义：`small` 保存较小的一半，`large` 保存较大的一半。
 * - 平衡规则：两堆大小差不超过 1，且 `small` 的堆顶不大于 `large` 的堆顶。
 * - 复杂度：`addNum` 和 `findMedian` 都是 O(1)~O(log n) 级别。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_295 {

    class MedianFinder {
        private PriorityQueue<Integer> small;
        private PriorityQueue<Integer> large;

        public MedianFinder() {
            small = new PriorityQueue<>((a, b) -> b - a);
            large = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
            } else {
                large.offer(num);
            }

            if (small.size() > large.size() + 1) {
                // 让两边数量尽量平衡
                large.offer(small.poll());
            } else if (large.size() > small.size()) {
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if (small.size() == large.size()) {
                return (small.peek() + large.peek()) / 2.0;
            }
            return small.peek();
        }
    }
}
