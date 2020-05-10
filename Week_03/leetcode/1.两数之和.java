import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        if (len < 2) return new int[0];
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (m.containsKey(target - nums[i])) {
                return new int[]{m.get(target - nums[i]), i};
            } else {
                m.put(nums[i], i);
            }
        }
        return new int[0];
    }
}
// @lc code=end

