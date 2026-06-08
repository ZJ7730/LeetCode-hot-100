import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_763
 * @description: 763. 划分字母区间
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
                result.add(end - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
