import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (74.70%)
 * Likes:    689
 * Dislikes: 0
 * Total Accepted:    123.5K
 * Total Submissions: 162.6K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len < 1) return res;
        Deque<Integer> deque = new LinkedList<Integer>();
        boolean []used = new boolean[len];
        helper(nums, len, res, deque, used);
        return res;
    }

    public void helper(int[] nums, int n, List<List<Integer>> res, Deque<Integer> deque, boolean[] used) {
        if (deque.size() == n) {
            List<Integer> list = new ArrayList<Integer>(deque);
            res.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            deque.addLast(nums[i]);
            helper(nums, n, res, deque, used);
            used[i] = false;
            deque.removeLast();
        }
    }
}
// @lc code=end


// 回溯法求解1
// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         int len = nums.length;
//         if (len < 1) return res;
//         int []arr = new int[len];
//         helper(nums, res, arr, 0);
//         return res;
//     }

//     public void helper(int[] nums, List<List<Integer>> res, int []arr, int i) {
//         if (i == nums.length) {
//             List<Integer> list = new ArrayList<Integer>();
//             for (int v : arr) {
//                 list.add(v);
//             }
//             res.add(list);
//             return;
//         }
//         for (int j = i; j < nums.length; j++) {
//             arr[i] = nums[j];
//             swap(nums, i, j);
//             helper(nums, res, arr, i + 1);
//             swap(nums, i, j);
//         }
//     } 

//     public void swap(int[] nums, int i, int j) {
//         int tmp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = tmp;
//     }
// }



//代码收藏，不使用数组标志是否访问过
// class Solution {
//     public void backtrack(int n,
//                           ArrayList<Integer> output,
//                           List<List<Integer>> res,
//                           int first) {
//       // 所有数都填完了
//       if (first == n)
//         res.add(new ArrayList<Integer>(output));
//       for (int i = first; i < n; i++) {
//         // 动态维护数组
//         Collections.swap(output, first, i);
//         // 继续递归填下一个数
//         backtrack(n, output, res, first + 1);
//         // 撤销操作
//         Collections.swap(output, first, i);
//       }
//     }
  
//     public List<List<Integer>> permute(int[] nums) {
//       List<List<Integer>> res = new LinkedList();
  
//       ArrayList<Integer> output = new ArrayList<Integer>();
//       for (int num : nums)
//         output.add(num);
  
//       int n = nums.length;
//       backtrack(n, output, res, 0);
//       return res;
//     }
//   }





