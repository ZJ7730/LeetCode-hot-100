import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @program: suanfa
 * @ClassName: H_207
 * @description: 207. 课程表
 *
 * 面试笔记：
 * - 题目定位：判断 numCourses 门课程是否都能学完。课程之间存在先修依赖，如果依赖关系形成环，
 *   比如 A 依赖 B、B 又依赖 A，就永远找不到可以先学的课程，因此本题本质是判断有向图是否有环。
 * - 核心思路：使用 BFS 拓扑排序。拓扑排序的过程就是不断找到“当前没有前置依赖”的课程，
 *   先把它学掉，再用它去解锁后续课程。
 * - 建图方向：prerequisites 中 [cur, pre] 表示学习 cur 之前必须先学 pre，所以边是 pre -> cur。
 *   代码里的 graph[pre].add(cur) 表示学完 pre 之后，cur 这门课的一个前置条件被满足了。
 * - 入度含义：indegree[i] 表示课程 i 当前还剩多少门前置课程没有完成。
 *   入度为 0 表示这门课现在可以直接学；入度不为 0 表示还需要等待其他课程先完成。
 * - 队列含义：queue 中存放的是当前所有可以学习的课程，也就是入度已经变成 0 的课程。
 *   每次从队列弹出一门课，都等价于把这门课加入学习顺序。
 * - 处理规则：每学完一门课程 poll，就遍历 graph[poll] 中所有被它解锁的后续课程 next，
 *   将 indegree[next] 减一；如果减到 0，说明 next 的所有前置课程都已经学完，可以入队。
 * - 判断规则：res 记录已经成功学习的课程数量。如果最终 res == numCourses，说明所有课程都能被拓扑排序处理完，
 *   没有环；如果 res 小于 numCourses，说明剩余课程的入度无法归零，它们被环形依赖卡住了。
 * - 注意事项：邻接表存的是“学完当前课程后能解锁哪些课程”，不是当前课程依赖哪些课程；
 *   [cur, pre] 的边方向不能写反；最后要比较处理课程数，而不是只看队列是否为空。
 * - 复杂度：时间 O(numCourses + prerequisites.length)，每门课和每条依赖最多处理一次；
 *   空间 O(numCourses + prerequisites.length)，用于邻接表、入度数组和队列。
 *
 * @author: zhoujie07
 * @create: 2026-05-26
 **/
public class H_207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. graph[i] 表示：学完课程 i 后，可以继续学习哪些课程
        List<List<Integer>> graph = new ArrayList<>();

        // 2. 先给每一门课程准备一个列表，用来存放它能解锁的后续课程
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 3. indegree[i] 表示课程 i 还有多少门前置课程没有学完
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            // 4. [cur, pre] 表示要学 cur，必须先学 pre，所以建边 pre -> cur
            int cur = prerequisite[0];
            int pre = prerequisite[1];
            graph.get(pre).add(cur);
            // 5. cur 多了一个前置课程，入度加一
            indegree[cur]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // 6. 入度为 0 的课程没有前置依赖，可以作为拓扑排序的起点
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int res = 0;
        while (!queue.isEmpty()) {
            // 7. 从队列中取出一门当前可以学习的课程
            Integer poll = queue.poll();
            // 8. 每弹出一门课，表示这门课已经被成功学习
            res++;

            List<Integer> list = graph.get(poll);
            for (Integer next : list) {
                // 9. 当前课程学完后，它解锁的后续课程少了一个前置依赖
                indegree[next]--;
                if (indegree[next] == 0) {
                    // 10. 如果后续课程的前置依赖都完成了，就可以加入队列继续处理
                    queue.offer(next);
                }
            }
        }

        // 11. 如果能处理完所有课程，说明不存在环；否则剩下的课程互相依赖，无法学完
        return res == numCourses;
    }


}
