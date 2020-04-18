/*
 * @lc app=leetcode.cn id=26 lang=java
 *
 * [26] 删除排序数组中的重复项
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 1) return 0;
        int j = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i-1]) continue;
            nums[j++] = nums[i];
        }
        return j;
    }
}
// @lc code=end

