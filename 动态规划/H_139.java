import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_139
 * @description: 139. 单词拆分
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_139 {

    /**
     * 代码随想录动规五部曲：
     * 1. dp数组含义：dp[i] 表示字符串 s 的前 i 个字符 s[0..i) 是否可以被字典拆分。
     * 2. 递推公式：如果存在 j < i，且 dp[j] 为 true，同时 s[j..i) 在 wordDict 中，
     *    那么 dp[i] = true。
     * 3. 初始化：dp[0] = true，空串作为递推起点。
     * 4. 遍历顺序：先遍历背包容量 i，再枚举切分点 j；本题强调单词排列顺序。
     * 5. 解题思路：枚举最后一个单词的起点，只要前缀能拆，最后一段也在字典里，当前前缀就能拆。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = false;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i && !dp[i]; j++) {
                if (wordDict.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];


    }

}
