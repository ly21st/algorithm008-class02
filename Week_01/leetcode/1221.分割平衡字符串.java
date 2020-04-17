/*
 * @lc app=leetcode.cn id=1221 lang=java
 *
 * [1221] 分割平衡字符串
 */

// @lc code=start
class Solution {
    public int balancedStringSplit(String s) {
        int balanceCount = 0;
        int len = s.length();
        int lCnt = 0;
        int rCnt = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'L') {
                lCnt += 1;
            } else {
                rCnt += 1;
            }
            if (lCnt == rCnt) {
                balanceCount += 1;
                lCnt = 0;
                rCnt = 0;
            }
        }
        return balanceCount;
    }
}
// @lc code=end



// class Solution {
//     public int balancedStringSplit(String s) {
//         Stack<Character> stack = new Stack<Character>();
//         int count = 0;
//         for(int i = 0; i < s.length(); i++){
//             char c = s.charAt(i);
//             if(stack.isEmpty() || c == stack.peek()) {
//                 stack.push(c);
//             }
//             else{
//                 stack.pop();
//             }
//             if(stack.isEmpty()) {
//                 count++;
//             }
//         }
//         return count;
//     }
// }