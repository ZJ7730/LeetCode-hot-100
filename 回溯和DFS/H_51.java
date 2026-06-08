import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_51
 * @description: 51. N 皇后
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_51 {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = '.';
            }
        }
        backtrack(0, board, result);
        return result;
    }

    private void backtrack(int row, char[][] board, List<List<String>> result) {
        if (row == board.length) {
            result.add(buildBoard(board));
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (!isValid(row, col, board)) {
                continue;
            }
            board[row][col] = 'Q';
            backtrack(row + 1, board, result);
            board[row][col] = '.';
        }
    }

    private boolean isValid(int row, int col, char[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> buildBoard(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
}
