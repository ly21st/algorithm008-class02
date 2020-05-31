/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 *
 * https://leetcode-cn.com/problems/house-robber/description/
 *
 * algorithms
 * Easy (44.23%)
 * Likes:    790
 * Dislikes: 0
 * Total Accepted:    113.8K
 * Total Submissions: 254.6K
 * Testcase Example:  '[1,2,3,1]'
 *
 * 
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * 
 * 示例 1:
 * 
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 
 * 示例 2:
 * 
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 1) return 0;
        if (len < 2) return nums[0];
        if (len < 3) {
            return nums[0] >= nums[1]? nums[0] : nums[1];
        } 
        int []max = new int[len];
        max[0] = nums[0];
        max[1] = nums[1];
        for (int i = 2; i < len; i++) {
            if (i == 2) {
                max[i] = max[i - 2] + nums[i];
            } else {
                max[i] = Math.max(max[i - 3], max[i - 2]) + nums[i];
            }
        }
        return max[len - 2] > max[len - 1]? max[len - 2] : max[len - 1]; 
    }
}

// @lc code=end

// 动态规划法
// class Solution {
//     public int rob(int[] nums) {
//         int len = nums.length;
//         if (len < 1) return 0;
//         if (len < 2) return nums[0];
//         if (len < 3) {
//             return nums[0] >= nums[1]? nums[0] : nums[1];
//         } 
//         int []max = new int[len];
//         max[0] = nums[0];
//         max[1] = nums[1];
//         for (int i = 2; i < len; i++) {
//             if (i == 2) {
//                 max[i] = max[i - 2] + nums[i];
//             } else {
//                 max[i] = Math.max(max[i - 3], max[i - 2]) + nums[i];
//             }
//         }
//         return max[len - 2] > max[len - 1]? max[len - 2] : max[len - 1]; 
//     }
// }


// 回溯法，压测时超时
// class Solution {
//     int max = 0; 
//     public int rob(int[] nums) {
//         int len = nums.length;
//         if (len < 1) return 0;
//         helper(nums, len, 0, 0);
//         return max;
//     }

//     public void helper(int[] nums, int len, int curCnt, int first) {
//         if (first >= len) {
//             if (curCnt > max) {
//                 max = curCnt;
//                 return;
//             }
//         }
//         for (int i = first; i < len; i++) {
//             helper(nums, len, curCnt + nums[i], i + 2);
//         }
//     }
// }