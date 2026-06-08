/**
 * @program: suanfa
 * @ClassName: H_11
 * @description: 盛最多水的容器
 * @author: zhoujie07
 * @create: 2026-05-17
 **/
public class H_11 {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;

        while (left < right) {
            int a = (right - left) * Math.min(height[left], height[right]);
            res = Math.max(res, a);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }


}
