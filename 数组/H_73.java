/**
 * @program: suanfa
 * @ClassName: H_73
 * @description: 73. 矩阵置零
 *
 * 面试笔记：
 * - 题目定位：只要某个位置为 0，就把它所在的行和列都置为 0。
 * - 核心思路：用第一行和第一列充当标记位，避免额外开空间。
 * - 为什么要额外记录首行首列：它们本身既是数据区，也是标记区，需要单独区分是否要整体清零。
 * - 处理顺序：先记录首行首列是否有 0，再用它们做标记，最后再处理首行首列。
 * - 复杂度：时间 O(mn)，空间 O(1)。
 *
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_73 {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        for (int col = 0; col < n; col++) {
            if (matrix[0][col] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int row = 0; row < m; row++) {
            if (matrix[row][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    // 用首行首列记录当前位置所在行列需要清零
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }

        if (firstRowZero) {
            for (int col = 0; col < n; col++) {
                matrix[0][col] = 0;
            }
        }
        if (firstColZero) {
            for (int row = 0; row < m; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
