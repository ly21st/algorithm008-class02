/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int i, j;
        for (i = 0, j = 0; j < len; j++) {
            if (nums[j] != 0) {
                if (i != j) {
                    nums[i] = nums[j];
                    nums[j] = 0;
                }
                i++;
            }
        }
    }
}
// @lc code=end



//class Solution {
//     public void moveZeroes(int[] nums) {
//         int len = nums.length;
//         for (int i = 0, j = 0; j < len; j++) {
//             if (nums[j] != 0) {
//                 nums[i] = nums[j];
//                 if (j != i) {
//                     nums[j] = 0;
//                 }
//                 i++;
//             }
//         }
//     }
// }







// class Solution {
//     public void moveZeroes(int[] nums) {
//         int len = nums.length;
//         int zeroCount = 0;
//         for (int j = 0; j < len; j++) {
//             if (nums[j] != 0) {
//                 int tmp = nums[j - zeroCount];
//                 nums[j - zeroCount] = nums[j];
//                 nums[j] = tmp;
//             } else {
//                 zeroCount++;
//             }
//         }
//     }
// }



// class Solution {
//     public void moveZeroes(int[] nums) {
//         int len = nums.length;
//         for (int i = 0, j = 0; j < len; j++) {
//             if (nums[j] != 0) {
//                 int tmp = nums[i];
//                 nums[i] = nums[j];
//                 nums[j] = tmp;
//                 i++;
//             } 
//         }
//     }
// }