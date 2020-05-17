/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Hard (33.68%)
 * Likes:    554
 * Dislikes: 0
 * Total Accepted:    60.2K
 * Total Submissions: 165.9K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 示例:
 * 
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 
 * 说明:
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 */

// @lc code=start
// 参考官方解法实现
class Solution {
    int steps = 0;
    public int jump(int[] nums) {
        int len = nums.length;
        if (len < 2) return 0;
        int end = 0; 
        int maxPosition = 0;
        
        for (int i = 0; i < len - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end ) {
                steps++;
                end = maxPosition;
            }
        }
        return steps;
    }
}
// @lc code=end


// 贪心法，效率较差实现
// class Solution {
//     int count = 0;
//     public int jump(int[] nums) {
//         int len = nums.length;
//         if (len < 2) return 0;
//         int j = len - 1;
//         while (j > 0) {
//             int minIndex = j - 1;
//             for (int i = j - 1; i >= 0; i--) {
//                 if (nums[i] >= j - i && i <= minIndex) {
//                     minIndex = i;
//                 }
//             } 
//             count++;
//             j = minIndex;
//         }
//         return count;
//     }
// }




// 贪心法，从前往后，参考官方解法我的实现，比官方解法好理解
class Solution {
    int count = 0;
    public int jump(int[] nums) {
        int len = nums.length;
        int i = 0; 
        int maxPosition = 0;
        while (i < len - 1) {
            if (i + nums[i] >= len - 1 ) {
                count++;
                break;
            }
            int maxi = i + 1;
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (j + nums[j] > maxPosition) {
                    maxPosition = j + nums[j];
                    maxi = j;
                }
            }
            i = maxi;
            count++;
        }
        return count;
    }
}


// 从前往后官方解法
// public int jump(int[] nums) {
//     int end = 0;
//     int maxPosition = 0; 
//     int steps = 0;
//     for(int i = 0; i < nums.length - 1; i++){
//         //找能跳的最远的
//         maxPosition = Math.max(maxPosition, nums[i] + i); 
//         if( i == end){ //遇到边界，就更新边界，并且步数加一
//             end = maxPosition;
//             steps++;
//         }
//     }
//     return steps;
// }

