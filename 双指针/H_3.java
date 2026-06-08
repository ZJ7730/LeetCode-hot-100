import java.util.HashSet;

/**
 * @program: suanfa
 * @ClassName: H_3
 * @description: 无重复字符的最长子串 滑动窗口算法
 * @author: zhoujie07
 * @create: 2026-05-15
 **/
public class H_3 {


    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> window = new HashSet<>();

        int left = 0;
        int res = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            while (window.contains(rightChar)) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(rightChar);
            res = Math.max(res, right - left + 1);
        }
        return res;

    }


}
