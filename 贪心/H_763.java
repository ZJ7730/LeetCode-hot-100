import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_763
 * @description: 763. 划分字母区间
 *
 * 面试笔记：
 * - 题目定位：把字符串切成尽可能多的片段，要求每个字母只出现在一个片段中。
 * - 核心思路：先统计每个字符最后出现的位置，再线性扫描维护当前片段的右边界。
 * - 片段结束条件：当扫描位置 i 等于当前最远边界 end 时，说明这一段已经包含了所有需要的字符，可以切分。
 * - 为什么可行：片段内所有字符的最后出现位置都不会越过 end，因此在 end 处切开不会影响后续字符约束。
 * - 复杂度：时间 O(n)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_763 {

    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                // 当前片段已经覆盖了所有相关字符
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
