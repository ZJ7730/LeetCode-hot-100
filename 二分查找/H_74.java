/**
 * @program: suanfa
 * @ClassName: H_74
 * @description: 74. 搜索二维矩阵
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
