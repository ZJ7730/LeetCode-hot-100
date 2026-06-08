import java.util.HashMap;
import java.util.Map;

/**
 * @program: suanfa
 * @ClassName: H_1
 * @description: 两数之和
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
                map.put(nums[i], i);
            }
        }
        return null;
    }


}
