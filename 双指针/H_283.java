/**
 * @program: suanfa
 * @ClassName: H_283
 * @description: 移动零
 * @author: zhoujie07
 * @create: 2026-05-17
 **/
public class H_283 {

    public void moveZeroes(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
        }

        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
