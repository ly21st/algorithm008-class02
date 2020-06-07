import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=547 lang=java
 *
 * [547] 朋友圈
 *
 * https://leetcode-cn.com/problems/friend-circles/description/
 *
 * algorithms
 * Medium (56.71%)
 * Likes:    247
 * Dislikes: 0
 * Total Accepted:    44.4K
 * Total Submissions: 78.2K
 * Testcase Example:  '[[1,1,0],[1,1,0],[0,0,1]]'
 *
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是
 * C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j
 * 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: 
 * [[1,1,0],
 * ⁠[1,1,0],
 * ⁠[0,0,1]]
 * 输出: 2 
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: 
 * [[1,1,0],
 * ⁠[1,1,1],
 * ⁠[0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 
 * 
 * 注意：
 * 
 * 
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length; 
        int []parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0; 
        for (int i = 0; i < n; i++) {
            if (parent[i] == -1) {
                count++;
            }
        }
        return count;
    }

    public void union(int[]parent, int i, int j) {
        int setX = find(parent, i);
        int setY = find(parent, j);
        if (setX != setY) {
            parent[setX] = setY;
        }
    }

    public int find(int []parent, int i) {
        while (parent[i] != -1) {
            i = parent[i];
        }
        return i;
    }
}
// @lc code=end

// 深度优先遍历
// class Solution {
//     public int findCircleNum(int[][] M) {
//         int m = M.length; 
//         if (m < 1) return 0;
//         int n = M[0].length;
//         if (n < 1) return 0;
//         return helper(m, n, M);
//     }

//     public int helper(int m, int n, int[][]M) {
//         int count = 0;
//         for (int i = 0; i < m; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (M[i][j] == 1) {
//                     dfs(n, M, i, j);
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }

//     public void dfs(int n, int[][]M, int i, int j) {
//         M[i][i] = 0; 
//         M[j][j] = 0;
//         M[i][j] = 0; 
//         M[j][i] = 0;
//         for (int ii = 0; ii < n; ii++) {
//             if (M[i][ii] == 1) {
//                 dfs(n, M, i, ii);
//             }
//             if (M[j][ii] == 1) {
//                 dfs(n, M, j, ii);
//             }
//         }
//     }
// }