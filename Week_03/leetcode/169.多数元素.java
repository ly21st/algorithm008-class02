import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 多数元素
 *
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (62.88%)
 * Likes:    584
 * Dislikes: 0
 * Total Accepted:    163.1K
 * Total Submissions: 258.2K
 * Testcase Example:  '[3,2,3]'
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: [3,2,3]
 * 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * 
 * 
 */

// @lc code=start
class Solution {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                cnt += 1;
                if (cnt >= len / 2) return nums[i];
            } else {
                cnt = 1;
            }
        }
        return nums[0];
    }
}
// @lc code=end


// map方法
// class Solution {
//     public int majorityElement(int[] nums) {
//         Map<Integer, Integer> m = new HashMap<Integer, Integer>();
//         int maxCnt = 0;
//         int len = nums.length;
//         for (int i = 0; i < len; i++) {
//             int cnt = m.getOrDefault(nums[i], 0);
//             if (cnt + 1 > len / 2) return nums[i];
//             m.put(nums[i], cnt + 1);
//         }
//         return nums[0];
//     }
// }