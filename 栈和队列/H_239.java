import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_239
 * @description: 239. 滑动窗口最大值
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
