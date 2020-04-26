/*
 * @lc app=leetcode.cn id=1021 lang=java
 *
 * [1021] 删除最外层的括号
 */

// @lc code=start
class Solution {
    public String removeOuterParentheses(String S) {
        int i = 0, j; 
        int count = 0; 
        StringBuffer s = new StringBuffer();
        char []cArr = S.toCharArray();
        int len = cArr.length;
        for (j = 0; j < len; j++) {
            if (cArr[j] == '(') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                s.append(S.substring(i+1, j));
                i = j + 1;
            }
        }
        return s.toString();
    }
}
// @lc code=end




// class Solution {
//     public String removeOuterParentheses(String S) {
//         int len = S.length();
//         int i = 0, j; 
//         int left = 0, right = 0; 
//         StringBuffer s = new StringBuffer();
//         for (j = 0; j < len; j++) {
//             if (S.charAt(j) == '(') {
//                 left++;
//             } else {
//                 right++;
//             }
//             if (left == right) {
//                 s.append(S.substring(i+1, j));
//                 i = j + 1;
//             }
//         }
//         return s.toString();
//     }
// }




// class Solution {
//     public String removeOuterParentheses(String S) {
//         int len = S.length();
//         int i = 0, j; 
//         int count = 0; 
//         StringBuffer s = new StringBuffer();
//         for (j = 0; j < len; j++) {
//             if (S.charAt(j) == '(') {
//                 count++;
//             } else {
//                 count--;
//             }
//             if (count == 0) {
//                 s.append(S.substring(i+1, j));
//                 i = j + 1;
//             }
//         }
//         return s.toString();
//     }
// }