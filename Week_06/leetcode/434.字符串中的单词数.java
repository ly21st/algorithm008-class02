/*
 * @lc app=leetcode.cn id=434 lang=java
 *
 * [434] 字符串中的单词数
 *
 * https://leetcode-cn.com/problems/number-of-segments-in-a-string/description/
 *
 * algorithms
 * Easy (35.06%)
 * Likes:    51
 * Dislikes: 0
 * Total Accepted:    16.8K
 * Total Submissions: 47.7K
 * Testcase Example:  '"Hello, my name is John"'
 *
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * 
 * 示例:
 * 
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 * 
 * 
 */

// @lc code=start
// 利用库函数与正则表达式，效率较差 
class Solution {
    public int countSegments(String s) {
        s = s.trim();
        if (s.equals("")) return 0; 
        return s.split("\\s++").length;
    }
}
// @lc code=end


// 方法1
// class Solution {
//     public int countSegments(String s) {
//         char []arr =  s.toCharArray();
//         int len = arr.length;
//         int count = 0;
//         for (int i = 0; i < len; i++) {
//             if (i == 0 && arr[i] != ' ') count++;
//             else if (i > 0 && arr[i] != ' ' && arr[i - 1] == ' ') {
//                 count++;
//             }
//         }
//         return count;
//     }
// }

