/**
 * @program: suanfa
 * @ClassName: H_73
 * @description: 73. 矩阵置零
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
