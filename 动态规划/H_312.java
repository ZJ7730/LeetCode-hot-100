/**
 * @program: suanfa
 * @ClassName: H_312
 * @description: 312. 戳气球
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_312 {
    /**
     * 待实现思路，参考代码随想录动规五部曲：
     * 1. dp数组含义：dp[i][j] 表示戳破开区间 (i, j) 内所有气球能获得的最大金币。
     * 2. 递推公式：枚举 k 作为区间 (i, j) 中最后一个被戳破的气球，
     *    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j])。
     * 3. 初始化：开区间内没有气球时收益为 0。
     * 4. 遍历顺序：区间 DP，要先算小区间再算大区间；通常 i 从后往前，j 从前往后，或按区间长度枚举。
     * 5. 解题思路：关键是枚举最后戳哪个气球，这样左右两侧区间才互不影响。
     */
}
