import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_56
 * @description: 56. 合并区间
 *
 * 面试笔记：
 * - 题目定位：给定若干区间，合并所有有重叠的区间。
 * - 核心思路：先按照左端点升序排序，这样可能重叠的区间一定会相邻，再从左到右维护当前正在合并的区间。
 * - 合并规则：如果下一个区间的左端点 nextStart <= currentEnd，说明两个区间有交集，只需要扩展右端点。
 * - 收尾规则：如果不能合并，先把当前区间加入结果，再用下一个区间开启新的合并区间；循环结束后别忘了加入最后一个区间。
 * - 复杂度：时间 O(nlogn)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_56 {

    public int[][] merge(int[][] intervals) {
        // 1. 按照区间左端点升序排序，保证能合并的区间在排序后一定相邻
        Arrays.sort(intervals, (a, b) ->
                Integer.compare(a[0], b[0]));


        List<int[]> res = new ArrayList<>();

        // 2. 先用第一个区间作为当前正在合并的区间
        int currentStart = intervals[0][0];
        int currentEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (nextStart <= currentEnd) {
                // 3. 下一个区间左端点没有超过当前右端点，说明有重叠，更新更远的右端点
                currentEnd = Math.max(currentEnd, nextEnd);
            } else {
                // 4. 没有重叠时，当前区间已经合并完成，加入结果
                res.add(new int[]{currentStart, currentEnd});

                // 5. 用下一个区间开启新的待合并区间
                currentStart = nextStart;
                currentEnd = nextEnd;
            }
        }

        // 6. 循环中只会在遇到不重叠时加入结果，最后一个合并区间需要单独加入
        res.add(new int[]{currentStart, currentEnd});
        return res.toArray(new int[][]{});
    }


}
