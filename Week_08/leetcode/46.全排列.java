/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (76.09%)
 * Likes:    734
 * Dislikes: 0
 * Total Accepted:    136.1K
 * Total Submissions: 178.8K
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
        List<List<Integer>> res =  new ArrayList<List<Integer>>();
        int n = nums.length;
        int []output = new int[n];
        boolean []used = new boolean[n];
        dfs(nums, n, output, 0, res, used);
        return res;
    }

    public void dfs(int[] nums, int n, int[] output, int i, List<List<Integer>> res,
                    boolean []used) {
        if (i == n) {
            List<Integer> list = new ArrayList<>();
            for (int v : output) {
                list.add(v);
            }
            res.add(list);
        }
        for (int ii = 0; ii < n; ii++) {
            if (used[ii]) {
                continue;
            }
            output[i] = nums[ii];
            used[ii] = true;
            dfs(nums, n, output, i + 1, res, used);
            used[ii] = false;
        }
    }
}
// @lc code=end


// 方法1
// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res =  new ArrayList<List<Integer>>();
//         int n = nums.length;
//         int []output = new int[n];
//         boolean []used = new boolean[n];
//         dfs(nums, n, output, 0, res, used);
//         return res;
//     }

//     public void dfs(int[] nums, int n, int[] output, int i, List<List<Integer>> res,
//                     boolean []used) {
//         if (i == n) {
//             List<Integer> list = new ArrayList<>();
//             for (int v : output) {
//                 list.add(v);
//             }
//             res.add(list);
//         }
//         for (int ii = 0; ii < n; ii++) {
//             if (used[ii]) {
//                 continue;
//             }
//             output[i] = nums[ii];
//             used[ii] = true;
//             dfs(nums, n, output, i + 1, res, used);
//             used[ii] = false;
//         }
//     }
// }


// 方法2
// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> res =  new ArrayList<List<Integer>>();
//         int n = nums.length;
//         dfs(nums, n, 0, res);
//         return res;
//     }

//     public void dfs(int[] nums, int n, int i, List<List<Integer>> res) {
//         if (i == n) {
//             List<Integer> list = new ArrayList<>();
//             for (int v : nums) {
//                 list.add(v);
//             }
//             res.add(list);
//         }
//         for (int j = i; j < n; j++) {
//             if (j != i) {
//                 swap(nums, i, j);
//             }
//             dfs(nums, n, i + 1, res);
//             if (i != j) {
//                 swap(nums, i, j);
//             }
//         }
//     }

//     public void swap(int[] nums, int i, int j) {
//         int tmp = nums[i];
//         nums[i] = nums[j];
//         nums[j] = tmp;
//     }
// }