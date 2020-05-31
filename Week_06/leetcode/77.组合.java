import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (73.43%)
 * Likes:    273
 * Dislikes: 0
 * Total Accepted:    51.8K
 * Total Submissions: 70.3K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int []output = new int[k];
        helper(1, n, k, 0, output, res);
        return res;
    }

    public void helper(int start, int end, int k, int first, int[] output, List<List<Integer>> res) {
        if (first == k) {
            List<Integer> list = new ArrayList<Integer>();
            for (int v : output) {
                list.add(v);
            }
            res.add(list);
            return;
        }

        for (int i = start; i <= end - (k - first) + 1; i++) {
            output[first] = i;
            helper(i + 1, end, k, first + 1, output, res);
        }
    }
}
// @lc code=end


// 方法1
// class Solution {
//     public List<List<Integer>> combine(int n, int k) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         int []output = new int[k];
//         helper(1, n, k, 0, output, res);
//         return res;
//     }

//     public void helper(int start, int end, int k, int first, int[] output, List<List<Integer>> res) {
//         if (first == k) {
//             List<Integer> list = new ArrayList<Integer>();
//             for (int v : output) {
//                 list.add(v);
//             }
//             res.add(list);
//             return;
//         }
    
//         for (int i = start; i <= end; i++) {
//             output[first] = i;
//             helper(i + 1, end, k, first + 1, output, res);
//         }
//     }
// }
