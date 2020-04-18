/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int t = m + n -1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) {
                nums1[t--] = nums2[j--];
            } else {
                nums1[t--] = nums1[i--];
            }
        }
        if (j >= 0) {
            while (j >= 0) {
                nums1[t--] = nums2[j--];
            }
        }
    }
}
// @lc code=end




// class Solution {
//     public void merge(int[] nums1, int m, int[] nums2, int n) {
//       // two get pointers for nums1 and nums2
//       int p1 = m - 1;
//       int p2 = n - 1;
//       // set pointer for nums1
//       int p = m + n - 1;
  
//       // while there are still elements to compare
//       while ((p1 >= 0) && (p2 >= 0))
//         // compare two elements from nums1 and nums2 
//         // and add the largest one in nums1 
//         nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
  
//       // add missing elements from nums2
//       System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
//     }
//   }
  
