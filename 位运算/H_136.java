/**
 * @program: suanfa
 * @ClassName: H_136
 * @description: 136. 只出现一次的数字
 * @author: zhoujie07
 * @create: 2026-05-20
 **/
public class H_136 {

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

}
