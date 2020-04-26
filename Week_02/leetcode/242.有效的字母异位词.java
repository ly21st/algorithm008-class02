/*
 * @lc app=leetcode.cn id=242 lang=java
 *
 * [242] 有效的字母异位词
 */

// @lc code=start
class Solution {
    public boolean isAnagram(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2) return false;
        int []arr = new int[256];
        for (int i = 0; i < len1; i++ ) {
            arr[s.charAt(i)] += 1;
        }
        for (int i = 0; i < len2; i++) {
            if (--arr[t.charAt(i)] < 0) return false;
        }
        return true;
    }
}
// @lc code=end

