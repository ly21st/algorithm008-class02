import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 *
 * https://leetcode-cn.com/problems/3sum/description/
 *
 * algorithms
 * Medium (26.39%)
 * Likes:    2018
 * Dislikes: 0
 * Total Accepted:    199.5K
 * Total Submissions: 752.9K
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？请你找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 
 * 
 * 示例：
 * 
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 
 * 满足要求的三元组集合为：
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (len < 3) return res;
        Arrays.sort(nums);
        for (int p0 = 0; p0 < len - 2; p0++) {
            if (nums[p0] > 0) return res;
            if (p0 > 0) {
                if (nums[p0] == nums[p0-1])
                    continue;
            }
            int p1 = p0 + 1;
            int p2 = len - 1;
            while (p1 < p2) {
                if (p1 > p0 + 1) {
                    if (nums[p1] == nums[p1-1]) {
                        p1++;
                        continue;
                    }
                } 
                if (p2 < len - 1) {
                    if (nums[p2] == nums[p2+1]) {
                        p2--;
                        continue;
                    }
                }
                int sum = nums[p0] + nums[p1] + nums[p2];
                if ( sum == 0) {
                    List<Integer> e = new ArrayList<Integer>(3);
                    e.add(nums[p0]);
                    e.add(nums[p1]);
                    e.add(nums[p2]);
                    res.add(e);
                    p1++;
                    p2--;
                } else if (sum < 0) {
                    p1++;
                } else {
                    p2--;
                }
            }
        }
        return res;
    }
}
// @lc code=end


// 精彩代码收藏
// class Solution {
//     public static List<List<Integer>> threeSum(int[] nums) {
//         List<List<Integer>> ans = new ArrayList();
//         int len = nums.length;
//         if(nums == null || len < 3) return ans;
//         Arrays.sort(nums); // 排序
//         for (int i = 0; i < len ; i++) {
//             if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
//             if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
//             int L = i+1;
//             int R = len-1;
//             while(L < R){
//                 int sum = nums[i] + nums[L] + nums[R];
//                 if(sum == 0){
//                     ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
//                     while (L<R && nums[L] == nums[L+1]) L++; // 去重
//                     while (L<R && nums[R] == nums[R-1]) R--; // 去重
//                     L++;
//                     R--;
//                 }
//                 else if (sum < 0) L++;
//                 else if (sum > 0) R--;
//             }
//         }        
//         return ans;
//     }
// }