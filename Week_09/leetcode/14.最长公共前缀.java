/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (36.87%)
 * Likes:    954
 * Dislikes: 0
 * Total Accepted:    224.4K
 * Total Submissions: 608.7K
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 
 * 
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 
 * 
 * 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 */

// @lc code=start
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len < 1) {
            return "";
        }
        int firstLen = strs[0].length();
        boolean endFlag = false;
        for (int j = 0; j < firstLen; j++) {
            char c = strs[0].charAt(j);
            for (int i = 1; i < len; i++) {
                if (j >= strs[i].length()) {
                    endFlag = true;
                    break;
                }
                if (c != strs[i].charAt(j)) {
                    endFlag = true;
                    break;
                }
            }
            if (endFlag) {
                return strs[0].substring(0, j);
            }
        }
        return strs[0];
    }
}
// @lc code=end

