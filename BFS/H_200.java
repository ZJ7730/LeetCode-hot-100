/**
 * @program: suanfa
 * @ClassName: H_200
 * @description: 200. 岛屿数量
 *
 * 面试笔记：
 * - 题目定位：在由 '1' 和 '0' 组成的网格中，统计互相连通的陆地块数量。
 * - 核心思路：外层双重循环扫描每一个格子，遇到一个没有访问过的陆地，就说明发现了一座新岛屿。
 * - 搜索方式：发现新岛屿后，使用 DFS 从当前陆地出发，把上下左右相连的所有陆地都标记为已访问。
 * - 计数规则：只有外层扫描第一次遇到某座岛屿的陆地时才 res++，同一座岛屿的其余陆地会在 DFS 中被标记掉。
 * - 注意事项：DFS 中必须先判断边界，再访问 grid[x][y]；同时要及时标记 visited，避免递归重复访问。
 * - 复杂度：时间 O(m * n)，空间 O(m * n)，递归栈和 visited 都可能占用网格级别空间。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_200 {


    public int numIslands(char[][] grid) {

        // 1. visited 记录每个格子是否已经被 DFS 扫过，避免同一块陆地重复计数
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;

        // 2. 从左到右、从上到下扫描整个网格，寻找每一座岛屿的第一个未访问陆地
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    // 3. 当前格子是未访问陆地，说明发现一座新岛屿，先计数
                    res++;
                    // 4. 再通过 DFS 把这座岛屿连通的所有陆地全部标记为已访问
                    dfs(visited, i, j, grid);
                }
            }
        }
        return res;
    }


    public void dfs(boolean[][] visited, int x, int y, char[][] grid) {
        // 5. 先判断边界，越界位置不能继续访问 grid
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
            return;
        }

        // 6. 已访问过的格子不用重复处理；水域不是岛屿的一部分，也直接返回
        if (visited[x][y] || grid[x][y] == '0') {
            return;
        }

        // 7. 标记当前陆地，表示它已经归属于当前这座岛屿
        visited[x][y] = true;

        // 8. 向上下左右四个方向继续搜索相邻陆地
        dfs(visited, x, y + 1, grid);
        dfs(visited, x + 1, y, grid);
        dfs(visited, x, y - 1, grid);
        dfs(visited, x - 1, y, grid);
    }
}
