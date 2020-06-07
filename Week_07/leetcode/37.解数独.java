/*
 * @lc app=leetcode.cn id=37 lang=java
 *
 * [37] 解数独
 *
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (61.50%)
 * Likes:    433
 * Dislikes: 0
 * Total Accepted:    29.7K
 * Total Submissions: 48.2K
 * Testcase Example:  '[["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]'
 *
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 
 * 一个数独的解法需遵循如下规则：
 * 
 * 
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 
 * 
 * 空白格用 '.' 表示。
 * 
 * 
 * 
 * 一个数独。
 * 
 * 
 * 
 * 答案被标成红色。
 * 
 * Note:
 * 
 * 
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 * 
 * 
 */

// @lc code=start
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) return;
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] box = new int[9][10];

        // 数据预处理
        for(int y = 0; y < 9; y++) {
            for(int x = 0; x < 9; x++) {
                if(board[y][x] != '.') {
                    int num = board[y][x] - '0';
                    row[y][num] = 1;
                    col[x][num] = 1;
                    int boxIndex = 3 * (y / 3) + x / 3;
                    box[boxIndex][num] = 1;
                }
            }
        }

        dfs(board, 0, 0, row, col, box);
    }

    // 从左往右，从上到下
    public boolean dfs(char[][] board, int y, int x, int[][] row, int[][] col, int[][] box) {
        int nx = (x + 1) % 9;
        int ny = nx == 0 ? y + 1 : y; // nx==0时说明上一行已经遍历完成
        if(y == 9) {
            return true;
        }
        
        
        if(board[y][x] != '.') {
            return dfs(board, ny, nx, row, col, box);
        }else {
            for(int i = 1; i <= 9; i++) {
                if(row[y][i] == 0 && col[x][i] == 0 && box[3 * (y / 3) + x / 3][i] == 0) {
                    row[y][i] = 1;
                    col[x][i] = 1;
                    box[3 * (y / 3) + x / 3][i] = 1;
                    board[y][x] = (char)(i + '0');
                    if(dfs(board, ny, nx, row, col, box)) return true;
                    row[y][i] = 0;
                    col[x][i] = 0;
                    box[3 * (y / 3) + x / 3][i] = 0;
                    board[y][x] = '.';
                }
            }
        }
        return false; 
    }
}
// @lc code=end

