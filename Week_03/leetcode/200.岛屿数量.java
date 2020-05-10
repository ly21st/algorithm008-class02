/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 *
 * https://leetcode-cn.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (47.98%)
 * Likes:    548
 * Dislikes: 0
 * Total Accepted:    101.2K
 * Total Submissions: 205.4K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 
 * 此外，你可以假设该网格的四条边均被水包围。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        if (rows < 1) return 0;
        int cols = grid[0].length;
        if (cols < 1) return 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, rows, cols, i, j);
                }
            }
        }
        return count;
    }

    public void dfs(char[][]grid, int rows, int cols, int i, int j) {
        grid[i][j] = '0';
        if (i - 1 >= 0 && grid[i-1][j] > '0') dfs(grid, rows, cols, i - 1, j);
        if (i + 1 < rows && grid[i+1][j] > '0') dfs(grid, rows, cols, i + 1, j);
        if (j - 1 >= 0 && grid[i][j-1] > '0') dfs(grid, rows, cols, i, j - 1);
        if (j + 1 < cols && grid[i][j+1] > '0') dfs(grid, rows, cols, i, j + 1);
    }    
}
// @lc code=end




