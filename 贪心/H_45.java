/**
 * @program: suanfa
 * @ClassName: H_45
 * @description: 45. 跳跃游戏 II
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_45 {

    public int jump(int[] nums) {
        int steps = 0;
        int end = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                steps++;
                end = farthest;
            }
        }
        return steps;
    }
}
