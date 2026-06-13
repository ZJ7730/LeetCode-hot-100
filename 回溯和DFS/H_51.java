import java.util.ArrayList;
import java.util.List;

/**
 * @program: suanfa
 * @ClassName: H_51
 * @description: 51. N 皇后
 *
 * 面试笔记：
 * - 题目定位：在 n x n 棋盘上放置 n 个皇后，要求互不攻击。
 * - 核心思路：按行回溯，每一行只放一个皇后；放之前检查列、左上对角线、右上对角线是否合法。
 * - 状态含义：`board` 保存当前棋盘，`row` 表示正在处理第几行。
 * - 收集条件：当 `row == n` 时，说明所有行都已成功放置皇后。
 * - 复杂度：和有效解数量相关，检查合法性的代价决定单次扩展成本。
 *
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
