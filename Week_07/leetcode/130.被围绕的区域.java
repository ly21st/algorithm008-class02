/*
 * @lc app=leetcode.cn id=130 lang=java
 *
 * [130] 被围绕的区域
 *
 * https://leetcode-cn.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (39.95%)
 * Likes:    221
 * Dislikes: 0
 * Total Accepted:    37.1K
 * Total Submissions: 92.7K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * 解释:
 * 
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 */

// @lc code=start
class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        if (m < 2) return;
        int n = board[0].length;
        if (n < 2) return;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, m, n, i, j);
            }
         }
    }

    public void dfs(char[][]board, int m, int n, int i, int j) {
        if (board[i][j] == 'O' && valid(board, m, n, i, j)) {
            board[i][j] = 'X';
            if (i > 0 && valid(board, m, n, i - 1 , j)) {
                dfs(board, m, n, i - 1, j);
            }
            if (i < m - 1 && valid(board, m, n, i + 1, j)) {
                dfs(board, m, n, i + 1, j);
            }
            if (j > 0 && valid(board, m, n, i, j - 1)) {
                dfs(board, m, n, i, j - 1);
            }
            if (j < n - 1 && valid(board, m, n, i, j - 1)) {
                dfs(board, m, n, i, j + 1);
            }
        }
    }

    public boolean valid(char[][]board, int m, int n, int i, int j) {
        if (i == 0 || i == m - 1) return false;
        if (j == 0 || j == n - 1) return false;
        if (i == 1 && board[i - 1][j] == 'O') {
            return false;
        }
        if (i == m - 2 && board[i + 1][j] == 'O') {
            return false;
        }
        if (j == 1 && board[i][j - 1] == 'O') {
            return false;
        }
        if (j == n - 2 && board[i][j + 1] == 'O') {
            return false;
        }
        return true;
    }
}
// @lc code=end

