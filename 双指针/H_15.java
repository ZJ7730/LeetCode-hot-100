import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_15
 * @description: 15. 三数之和
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_15 {

    /**
     * @param nums 待查找三元组的整数数组
     * @return 所有不重复且和为零的三元组
     * @author zhoujie07
     * @description 排序后枚举第一个数，并用左右指针查找剩余两个数
     * @date 2026-05-26
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
