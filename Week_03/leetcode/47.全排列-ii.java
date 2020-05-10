import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (58.03%)
 * Likes:    283
 * Dislikes: 0
 * Total Accepted:    56K
 * Total Submissions: 95.9K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 
 * 示例:
 * 
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 * 
 */

// @lc code=start
// 网友代码修改
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Deque<Integer> output = new LinkedList<Integer>();
        boolean []used = new boolean[len];
        Arrays.sort(nums);
        helper(nums, len, res, output, used);
        return res;
    }

    public void helper(int[] nums, int n, List<List<Integer>> res, Deque<Integer> output, boolean[] used) {
        if (output.size() == n) {
            List<Integer> list = new ArrayList<Integer>(output);
            res.add(list);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) continue;
            used[i] = true;
            output.addLast(nums[i]);
            helper(nums, n, res, output, used);
            used[i] = false;
            output.removeLast();
        }
    }
}
// @lc code=end


//
// class Solution {
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         int len = nums.length;
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         List<Integer> output = new ArrayList<Integer>();
//         for (int i : nums) {
//             output.add(i);
//         }
//         helper(len, res, output, 0);
//         return res;
//     }

//     public void helper(int n, List<List<Integer>> res, List<Integer> output, int first) {
//         if (first == n) {
//             List<Integer> list = new ArrayList<Integer>(output);
//             res.add(list);
//             return;
//         }
//         Set<Integer> used = new HashSet<Integer>();

//         for (int i = first; i < n; i++) {
//             if (used.contains(output.get(i))) continue;
//             used.add(output.get(i));
//             Collections.swap(output, first, i);
//             helper(n, res, output, first + 1);
//             Collections.swap(output, first, i);
//         }
//     }
// }



// 网友代码修改
// class Solution {
//     public List<List<Integer>> permuteUnique(int[] nums) {
//         int len = nums.length;
//         List<List<Integer>> res = new ArrayList<List<Integer>>();
//         Deque<Integer> output = new LinkedList<Integer>();
//         boolean []used = new boolean[len];
//         Arrays.sort(nums);
//         helper(nums, len, res, output, used);
//         return res;
//     }

//     public void helper(int[] nums, int n, List<List<Integer>> res, Deque<Integer> output, boolean[] used) {
//         if (output.size() == n) {
//             List<Integer> list = new ArrayList<Integer>(output);
//             res.add(list);
//             return;
//         }

//         for (int i = 0; i < n; i++) {
//             if (used[i]) continue;
//             if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) continue;
//             used[i] = true;
//             output.addLast(nums[i]);
//             helper(nums, n, res, output, used);
//             used[i] = false;
//             output.removeLast();
//         }
//     }
// }