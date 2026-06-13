/**
 * @program: suanfa
 * @ClassName: H_136
 * @description: 136. 只出现一次的数字
 *
 * 面试笔记：
 * - 题目定位：数组中只有一个数字出现一次，其余数字都出现两次，找出这个只出现一次的数字。
 * - 核心思路：利用异或性质，两个相同的数异或为 0，0 和任意数异或仍然是它本身。
 * - 结果维护：把所有元素依次异或到一个变量里，成对出现的数字会被抵消掉。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-05-20
 **/
public class H_136 {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            // 相同数字会在异或中抵消
            res ^= nums[i];
        }
        return res;
    }

}
