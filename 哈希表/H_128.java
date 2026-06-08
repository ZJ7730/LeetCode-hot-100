import java.util.HashSet;

/**
 * @program: suanfa
 * @ClassName: H_128
 * @description: 128. 最长连续序列
 *
 * 核心思路：
 * 1. 先把所有数字放入 HashSet，支持 O(1) 查询某个数字是否存在。
 * 2. 只从“连续序列的起点”开始扩展，即当前数字 num 的前一个数 num - 1 不存在时才开始统计。
 * 3. 如果每个数字都盲目向后扩展，会退化到 O(n^2)；只从起点扩展，每个数字最多被访问一次。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_128 {

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();

        // 去重并提供常数级查询能力。
        for (int num : nums) {
            numSet.add(num);
        }

        int result = 0;


        for (Integer num : numSet) {
            // num - 1 不存在，说明 num 是某段连续序列的起点。
            if (!numSet.contains(num - 1)) {
                int current = num;
                int length = 1;

                while (numSet.contains(current + 1)) {
                    current++;
                    length++;
                }
                result = Math.max(result, length);
            }
        }
        return result;
    }
}
