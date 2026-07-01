import java.util.*;

/**
 * @program: suanfa
 * @ClassName: H_49
 * @description: 49. 字母异位词分组
 *
 * 面试笔记：
 * - 题目定位：把由相同字母组成、但排列顺序不同的字符串分到同一组。
 * - 核心思路：字母异位词排序后会得到同一个字符串，可以把排序后的字符串作为哈希表 key。
 * - Map 语义：key 是字符串排序后的标准形式，value 是所有属于这一类异位词的原字符串列表。
 * - 处理方式：遍历每个字符串，先转成字符数组并排序，再用 computeIfAbsent 找到对应分组并加入原字符串。
 * - 复杂度：设字符串个数为 n，单个字符串最大长度为 k，时间 O(n * klogk)，空间 O(nk)。
 * - 面试易错点：加入分组时要保存原字符串 str，不是保存排序后的 sortedStr。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);

            res.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(res.values());
    }

}
