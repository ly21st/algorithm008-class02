/*
 * @lc app=leetcode.cn id=493 lang=java
 *
 * [493] 翻转对
 *
 * https://leetcode-cn.com/problems/reverse-pairs/description/
 *
 * algorithms
 * Hard (25.28%)
 * Likes:    98
 * Dislikes: 0
 * Total Accepted:    4.8K
 * Total Submissions: 18.7K
 * Testcase Example:  '[1,3,2,3,1]'
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 
 * 你需要返回给定数组中的重要翻转对的数量。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 
 * 
 * 注意:
 * 
 * 
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        return mergeAndCount(nums, 0, n-1);
    }

    public int mergeAndCount(int[] nums, int first, int end) {
        if (end <= first) {
            return 0;
        }
        int mid = (first + end) / 2;
        int count = mergeAndCount(nums, first, mid) + mergeAndCount(nums, mid+1, end);
        int j;
        for (int i = first; i <= mid; i++) {
            for (j = mid + 1; j <= end; ) {
                if (nums[i] / 2 >= nums[j] && (nums[i]+1)/ 2 > nums[j]) {
                    j++;
                } else {
                    break;
                }
            }
            count += j - (mid + 1);
        }
        merge(nums, first, mid, end);
        return count;
    }

    public void merge(int []nums, int first, int mid, int end) {
        int []tmp = new int[end - first + 1];
        int k = 0; 
        int i = first;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            tmp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= end) {
            tmp[k++] = nums[j++];
        }
        for (int p = 0; p < k; p++) {
            nums[first++] = tmp[p];
        }
    }
}
// @lc code=end


// 暴力法
// class Solution {
//     public int reversePairs(int[] nums) {
//         int n = nums.length;
//         int count = 0;
//         for (int i = 0; i < n - 1; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 if ((nums[i] + 1) / 2 > nums[j]) {
//                     count++;
//                 }
//             }
//         }
//         return count;
//     }
// }