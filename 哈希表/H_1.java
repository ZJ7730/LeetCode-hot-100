import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_1
 * @description: 两数之和
 *
 * 面试笔记：
 * - 题目定位：在数组中找到两个数，使它们之和等于 target，并返回下标。
 * - 核心思路：遍历当前元素时，先查它的补数是否已经出现，再决定是否把当前元素放入 map。
 * - Map 语义：key 是数字本身，value 是这个数字出现的位置。
 * - 复杂度：时间 O(n)，空间 O(n)。
 *
 * @author: zhoujie07
 * @create: 2026-05-18
 **/
public class H_1 {


    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0 ; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            } else {
                // 先查补数，再记录当前数字
                map.put(nums[i], i);
            }
        }
        return null;
    }


}
