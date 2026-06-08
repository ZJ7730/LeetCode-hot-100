import java.util.PriorityQueue;

/**
 * @program: suanfa
 * @ClassName: H_295
 * @description: 295. 数据流的中位数
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
