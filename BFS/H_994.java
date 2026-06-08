import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_994
 * @description: 994. 腐烂的橘子
 * @author: zhoujie07
 * @create: 2026-06-07
 **/
public class H_994 {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    fresh++;
                } else if (grid[row][col] == 2) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                for (int[] direction : directions) {
                    int nextRow = orange[0] + direction[0];
                    int nextCol = orange[1] + direction[1];
                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || grid[nextRow][nextCol] != 1) {
                        continue;
                    }
                    grid[nextRow][nextCol] = 2;
                    fresh--;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
