import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (68.69%)
 * Likes:    396
 * Dislikes: 0
 * Total Accepted:    39K
 * Total Submissions: 56.2K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 
 * 
 * 上图为 8 皇后问题的一种解法。
 * 
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 示例:
 * 
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 * 
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步，可进可退。（引用自
 * 百度百科 - 皇后 ）
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        int[] cols = new int[n];
        int[] slash = new int[2 * n - 1];
        int[] backSlash = new int[2 * n - 1];
        int[][] m = new int[n][n];
        helper(n, m, res, cols, slash, backSlash, 0);
        return res;
    }

    public void helper(int n, int[][] m, List<List<String>> res, int[] cols, int[] slash, int[] backSlash, int level) {
        if (level == n) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                String s = "";
                for (int j = 0; j < n; j++) {
                    if (m[i][j] == 1) {
                        s += "Q";
                    } else {
                        s += ".";
                    }
                }
                list.add(s);
            }
            res.add(list);
        }
        for (int j = 0; j < n; j++) {
            if (cols[j] != 1 && slash[level + j] != 1 && backSlash[n - 1 + level - j] != 1) {
                m[level][j] = 1;
                cols[j] = 1;
                slash[level + j] = 1;
                backSlash[n - 1 + level - j] = 1;
                helper(n, m, res, cols, slash, backSlash, level + 1);
                m[level][j] =  0;
                cols[j] = 0;
                slash[level + j] = 0;
                backSlash[n - 1 + level - j] = 0;
            }
        }
    }
}
// @lc code=end

