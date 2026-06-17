/**
 * @program: suanfa
 * @ClassName: H_461
 * @description: 461. 汉明距离
 *
 * 面试笔记：
 * - 题目定位：计算两个整数二进制表示中，对应位置不同的个数。
 * - 核心思路：先对 x 和 y 做异或，相同位异或结果为 0，不同位异或结果为 1。
 * - 计数方法：汉明距离就变成统计异或结果中 1 的个数。
 * - 位运算技巧：res & (res - 1) 可以消掉 res 二进制中最右边的一个 1，每执行一次就说明找到一个不同位。
 * - 复杂度：时间 O(k)，k 是异或结果中 1 的个数；空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_461 {

    public int hammingDistance(int x, int y) {
        // 1. 异或后，二进制中为 1 的位置就是 x 和 y 不同的位置
        int res = x ^ y;
        int count = 0;

        while (res != 0) {
            // 2. 每次消掉 res 最右边的一个 1，相当于统计到一个不同的二进制位
            res = res & (res - 1);
            count++;
        }
        // 3. 消掉了多少个 1，汉明距离就是多少
        return count;
    }


}
