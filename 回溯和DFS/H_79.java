/**
 * @program: suanfa
 * @ClassName: H_79
 * @description: 79. 单词搜索
 *
 * 面试笔记：
 * - 题目定位：在二维字符网格中判断是否存在一条路径，可以按顺序拼出给定单词 word。
 *   路径每一步只能向上、下、左、右四个方向移动，并且同一个格子在一条路径中不能重复使用。
 * - 核心思路：这是网格 DFS + 回溯。外层遍历每一个格子，把所有等于 word 首字母的位置都当成搜索起点；
 *   从起点开始递归匹配 word[index]，如果当前位置匹配成功，就继续向四个方向匹配下一个字符。
 * - 递归含义：dfs(board, word, x, y, index) 表示当前尝试在坐标 (x, y) 匹配 word 的第 index 个字符，
 *   如果从这里出发能匹配完 word[index..最后]，就返回 true。
 * - 终止条件：坐标越界直接返回 false；当前格子字符和 word[index] 不相等也返回 false；
 *   如果 index 已经是 word 最后一个下标，并且当前字符匹配成功，说明整条单词路径已经找到。
 * - 回溯处理：为了防止同一条路径重复使用当前格子，先把 board[x][y] 临时改成 '#' 标记已访问；
 *   四个方向递归结束后，再恢复成原字符 chara，保证其他搜索起点和其他路径还能正常使用这个格子。
 * - 剪枝点：外层只有首字母匹配时才调用 dfs；dfs 内部先判断越界和字符不匹配，可以尽早结束无效分支。
 * - 注意事项：修改原网格做 visited 标记后必须恢复；判断最后一个字符要放在标记之前，
 *   因为最后一个字符匹配成功后不需要继续向四个方向搜索。
 * - 复杂度：设网格大小为 m * n，单词长度为 L，最坏时间 O(m * n * 4^L)，递归深度空间 O(L)。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_79 {


    public boolean exist(char[][] board, String word) {

        // 1. 枚举网格中的每一个格子，把可能的首字母位置作为 DFS 起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 2. 只有当前字符等于 word 的首字母时，才有必要从这里开始搜索
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    // 3. 只要任意一个起点能匹配完整单词，就可以直接返回 true
                    return true;
                }
            }
        }

        // 4. 所有起点都搜索失败，说明网格中不存在该单词路径
        return false;
    }

    public boolean dfs(char[][] board, String word, int x, int y, int index) {
        // 5. 先判断边界，越界位置不能访问 board
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        // 6. 当前格子必须匹配 word[index]，不匹配就没必要继续向下搜索
        if (board[x][y] != word.charAt(index)) {
            return false;
        }

        // 7. 当前字符已经匹配，并且 index 到达最后一位，说明整个单词已经匹配完成
        if (index == word.length() - 1) {
            return true;
        }

        // 8. 保存当前字符，后面回溯时要恢复现场
        char chara = board[x][y];
        // 9. 临时标记当前格子已访问，避免同一条路径重复使用同一个格子
        board[x][y] = '#';

        // 10. 从当前格子向下、上、右、左四个方向继续匹配 word 的下一个字符
        boolean result = dfs(board, word, x + 1, y, index + 1) ||
                dfs(board, word, x - 1, y, index + 1) ||
                dfs(board, word, x, y + 1, index + 1) ||
                dfs(board, word, x, y - 1, index + 1);

        // 11. 恢复当前格子，保证其他递归路径可以重新使用它
        board[x][y] = chara;
        // 12. 四个方向中只要有一个方向匹配成功，结果就是 true
        return result;
    }


}
