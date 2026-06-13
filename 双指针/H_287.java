/**
 * @program: suanfa
 * @ClassName: H_287
 * @description: 287. 寻找重复数
 *
 * 解题思路：
 * 1. 把数组看成一个“链表”，下标 i 指向 nums[i]。
 * 2. 由于数组长度为 n + 1，且元素范围是 [1, n]，所以必然存在重复数字。
 * 3. 重复数字会让这个“链表”出现环，问题就转化成寻找环的入口。
 * 4. 使用 Floyd 快慢指针：
 *    - 第一次相遇：判断链表中存在环。
 *    - 第二次相遇：一个指针从起点出发，另一个指针从相遇点出发，同步前进，入口就是重复数字。
 *
 * 复杂度：
 * - 时间 O(n)
 * - 空间 O(1)
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_287 {

    public int findDuplicate(int[] nums) {
        // 第一阶段：快慢指针在环内相遇
        int slow = 0;
        int fast = 0;
        do {
            // 慢指针每次走一步
            slow = nums[slow];
            // 快指针每次走两步
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 第二阶段：一个指针回到起点，和相遇点同步前进
        // 再次相遇的位置就是环入口，也就是重复数字
        int slowStart = 0;
        do {
            slowStart = nums[slowStart];
            slow = nums[slow];
        } while (slowStart != slow);

        return slowStart;
    }

}
