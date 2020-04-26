import java.util.Arrays;
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
        int []result = new int[2];
        if (len < 2) return result;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (m.containsKey(target - nums[i])) {
                result[0] = m.get(target - nums[i]);
                result[1] = i;
                return result;
            } 
            m.put(nums[i], i);
        }
        return result;
    }
}
// @lc code=end


