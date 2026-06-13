import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_54
 * @description: 54. 螺旋矩阵
 *
 * 面试笔记：
 * - 题目定位：按顺时针螺旋顺序遍历矩阵并返回结果。
 * - 核心思路：维护 `top/bottom/left/right` 四个边界，每次沿四条边依次收缩。
 * - 关键点：每缩小一条边界后，都要判断新的边界是否仍然有效，防止重复遍历。
 * - 复杂度：时间 O(mn)，空间 O(1) 不计结果。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // 从左到右
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // 从上到下
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            if (top <= bottom) {
                // 从右到左
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            if (left <= right) {
                // 从下到上
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        return result;
    }
}
