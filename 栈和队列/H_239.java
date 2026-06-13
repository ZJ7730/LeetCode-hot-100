import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_239
 * @description: 239. 滑动窗口最大值
 *
 * 面试笔记：
 * - 题目定位：求每个长度为 k 的窗口中的最大值。
 * - 核心思路：使用单调队列维护窗口内从大到小的元素，队首始终是当前最大值。
 * - 状态含义：队列保存的是候选最大值，入队时要删除所有比当前元素小的尾部元素。
 * - 复杂度：时间 O(n)，空间 O(k)。
 *
 * @author: zhoujie07
 * @create: 2026-05-19
 **/
public class H_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        int idx = 0;

        MonotonicQueue monotonicQueue = new MonotonicQueue();
        for (int i = 0; i < k; i++) {
            monotonicQueue.push(nums[i]);
        }

        result[idx++] = monotonicQueue.getMax();

        for (int i = k; i < nums.length; i++) {
            monotonicQueue.pop(nums[i - k]);
            monotonicQueue.push(nums[i]);
            result[idx++] = monotonicQueue.getMax();
        }

        return result;
    }


    class MonotonicQueue {
        Deque<Integer> queue = new LinkedList<>();

        public void push(int val) {
            while (!queue.isEmpty() && val > queue.getLast()) {
                // 维持队列从大到小的单调性
                queue.removeLast();
            }
            queue.addLast(val);
        }

        public void pop(int val) {
            if (!queue.isEmpty() && val == queue.peekFirst()) {
                queue.pollFirst();
            }
        }

        public Integer getMax() {
            return queue.peekFirst();
        }

    }

    public static void main(String[] args) {
        H_239 h_239 = new H_239();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = h_239.maxSlidingWindow(nums, 3);
        for (int i : result) {
            System.out.println(i);
        }
    }

}
