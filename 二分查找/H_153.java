/**
 * @program: suanfa
 * @ClassName: H_153
 * @description: 153. 寻找旋转排序数组中的最小值
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_153 {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
