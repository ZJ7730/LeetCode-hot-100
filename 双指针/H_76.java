/**
 * @program: suanfa
 * @ClassName: H_76
 * @description: 76. 最小覆盖子串
 *
 * 面试笔记：
 * - 题目定位：在 s 中找一个最短子串，必须覆盖 t 的全部字符及其出现次数。
 * - 变量定义：
 *   1. `need`：目标串 t 中每个字符需要的次数。
 *   2. `window`：当前窗口中每个字符出现的次数。
 *   3. `required`：t 中一共需要满足多少种字符。
 *   4. `formed`：当前窗口已经满足需求的字符种类数。
 * - 右扩张：不断加入 `right` 指向的字符，直到窗口第一次覆盖 t。
 * - 左收缩：只要窗口仍然合法，就持续移动 `left`，争取更短答案。
 * - 答案维护：每次窗口合法时，立刻比较当前长度，保留最短区间。
 * - 复杂度：时间 O(n)，空间 O(字符集大小)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_76 {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] need = new int[128];
        int[] window = new int[128];
        int required = 0;
        for (char c : t.toCharArray()) {
            if (need[c] == 0) {
                required++;
            }
            need[c]++;
        }

        int formed = 0;
        int left = 0;
        int bestStart = 0;
        int bestLen = Integer.MAX_VALUE;

        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            char c = chars[right];
            window[c]++;

            // 当前字符的需求被满足时，统计一个满足条件的字符种类
            if (need[c] > 0 && window[c] == need[c]) {
                formed++;
            }

            // 窗口已经覆盖 t，开始尝试收缩左边界
            while (formed == required) {
                int currentLen = right - left + 1;
                if (currentLen < bestLen) {
                    bestLen = currentLen;
                    bestStart = left;
                }

                char leftChar = chars[left];
                window[leftChar]--;
                // 左侧字符被移除后，窗口不再满足需求，停止收缩
                if (need[leftChar] > 0 && window[leftChar] < need[leftChar]) {
                    formed--;
                }
                left++;
            }
        }

        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }
}
