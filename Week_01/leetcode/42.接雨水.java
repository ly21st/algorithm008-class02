/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 */

// @lc code=start
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3)
            return 0;
        int i = 0;
        int j = len - 1;
        int sum = 0;
        while (i < len - 1 && height[i] == 0) i++;
        while (i + 1 < len) {
            while (i+1 < len && height[i] <= height[i+1]) i++;
            if (i + 1 == len) break;
            j = i + 1;
            int max = height[j];
            int max_index = j;
            while (j < len && height[j] < height[i]) {
                if (height[j] > max) {
                    max = height[j];
                    max_index = j;
                }
                j++;
            }
            if (j < len) {
                max = height[i];
                max_index = j; 
            }
            for (int k = i + 1; k < max_index; k++) {
                sum += max - height[k];
            }
            i = max_index;
            j = i + 1;
            if (j >= len) break;
            max = height[j];
            max_index = j;
        }
        return sum;
    }
}
// @lc code=end
