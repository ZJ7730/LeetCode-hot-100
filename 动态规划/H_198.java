/**
 * @program: suanfa
 * @ClassName: H_198
 * @description: 198. 打家劫舍
 * @author: zhoujie07
 * @create: 2026-05-28
 **/
public class H_198 {
    /**
     * 待实现思路，参考代码随想录动规五部曲：
     * 1. dp数组含义：dp[i] 表示考虑下标 0..i 的房屋，最多可以偷到的金额。
     * 2. 递推公式：第 i 间房可以偷也可以不偷，
     *    dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])。
     * 3. 初始化：dp[0] = nums[0]；dp[1] = Math.max(nums[0], nums[1])。
     * 4. 遍历顺序：从前往后。
     * 5. 解题思路：相邻房屋不能同时偷，所以当前最优来自“不偷当前”或“偷当前加上 i - 2 的最优”。
     */
}
