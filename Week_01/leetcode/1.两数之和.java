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
        int []result = new int[2];
        int len = nums.length;
        Map<Integer, Integer> n = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (n.containsKey(target - nums[i])) {
                result[0] = n.get(target - nums[i]);
                result[1] = i;
                break;
            } else {
                n.put(nums[i], i);
            }
        }
        return result;
    }
}
// @lc code=end





