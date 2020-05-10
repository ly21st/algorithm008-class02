import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (77.14%)
 * Likes:    558
 * Dislikes: 0
 * Total Accepted:    86.3K
 * Total Submissions: 111.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int n = nums.length;
        for (int i = (int) Math.pow(2, n); i < (int)Math.pow(2, n + 1); i++) {
            String s = Integer.toBinaryString(i).substring(1);

            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == '1') list.add(nums[j]);
            }
            res.add(list);
        }
        return res;
    }
}
// @lc code=end

