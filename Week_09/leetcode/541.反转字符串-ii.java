/*
 * @lc app=leetcode.cn id=541 lang=java
 *
 * [541] 反转字符串 II
 *
 * https://leetcode-cn.com/problems/reverse-string-ii/description/
 *
 * algorithms
 * Easy (53.45%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    16.4K
 * Total Submissions: 30.6K
 * Testcase Example:  '"abcdefg"\n2'
 *
 * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
 * 
 * 
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * 
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 该字符串只包含小写英文字母。
 * 给定字符串的长度和 k 在 [1, 10000] 范围内。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseStr(String s, int k) {
        int n = s.length();
        int i; 
        char []arr = s.toCharArray();
        for (i = 0; i + 2 * k <= n; i += 2 * k) {
            swap(arr, i, i + k -1);
        }
        if (i + k <= n) {
            swap(arr, i, i + k -1);
        } else {
            swap(arr, i, n - 1);
        }
        return new String(arr);
    }
    public void swap(char []arr, int first, int end) {
        for (int p0 = first, p1 = end; p0 < p1; p0++, p1--) {
            char c = arr[p0];
            arr[p0] = arr[p1];
            arr[p1] = c;
        }
    }
}
// @lc code=end

