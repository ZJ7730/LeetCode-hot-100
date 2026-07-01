import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_438
 * @description: 438. 找到字符串中所有字母异位词
 *
 * 面试笔记：
 * - 题目定位：在字符串 s 中找到所有长度等于 p.length()，并且字符组成和 p 相同的子串起始下标。
 * - 核心思路：异位词只关心字符出现次数，不关心顺序；用固定长度滑动窗口维护当前子串的字符频次。
 * - 窗口规则：右指针每次加入一个字符；当窗口长度超过 p 的长度时，移除 left 指向的字符并右移 left。
 * - 判断方式：当窗口长度等于 p.length() 时，比较 windowCount 和 targetCount 是否完全一致。
 * - 关键变量：targetCount 表示 p 的字符频次，windowCount 表示当前窗口的字符频次，left/right 维护窗口边界。
 * - 复杂度：时间 O(26 * n)，可视为 O(n)；空间 O(1)，因为只使用两个长度为 26 的数组。
 * - 面试易错点：窗口超过固定长度时必须先移除左侧字符，再判断当前窗口是否匹配。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_438 {


    /**
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();

        int[] targetCount = new int[26];
        int[] windowCount = new int[26];

        for (char e : p.toCharArray()) {
            targetCount[e - 'a']++;
        }

        int windowSize = p.length();
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char charred = s.charAt(right);
            windowCount[charred - 'a']++;

            if (right - left + 1 > windowSize) {
                char leftChar = s.charAt(left);
                windowCount[leftChar - 'a']--;
                left++;
            }

            if (right - left + 1 == windowSize && Arrays.equals(targetCount, windowCount)) {
                res.add(left);
            }
        }
        return res;
    }


}
