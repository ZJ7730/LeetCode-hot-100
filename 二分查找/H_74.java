/**
 * @program: suanfa
 * @ClassName: H_74
 * @description: 74. 搜索二维矩阵
 *
 * 面试笔记：
 * - 题目定位：在一个行升序、且每行首元素大于上一行末元素的二维矩阵里查找目标值。
 * - 核心思路：把二维矩阵按行展开成一维有序数组，再做标准二分。
 * - 下标映射：一维下标 `mid` 对应二维位置 `[mid / n][mid % n]`。
 * - 复杂度：时间 O(log(mn))，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 把一维 mid 映射回二维坐标
            int value = matrix[mid / n][mid % n];
            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
