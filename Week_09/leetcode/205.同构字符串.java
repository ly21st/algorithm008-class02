/*
 * @lc app=leetcode.cn id=205 lang=java
 *
 * [205] 同构字符串
 *
 * https://leetcode-cn.com/problems/isomorphic-strings/description/
 *
 * algorithms
 * Easy (47.18%)
 * Likes:    204
 * Dislikes: 0
 * Total Accepted:    40.9K
 * Total Submissions: 86.6K
 * Testcase Example:  '"egg"\n"add"'
 *
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 
 * 示例 1:
 * 
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 
 * 示例 3:
 * 
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 * 
 */

// @lc code=start
// 用数组方式
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int n = s.length();
        int[] m = new int[256];
        int[] visited = new int[256];
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (m[c1] == 0) {
                if (visited[c2] != 0) {
                    return false;
                }
                m[c1] = c2;
                visited[c2]++;
            } else {
                char c = (char)m[c1];
                if (c!= c2) {
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end


// 方法1，使用map与set
// class Solution {
//     public boolean isIsomorphic(String s, String t) {
//         int n = s.length();
//         Map<Character, Character> m = new HashMap<>();
//         Set<Character> visited = new HashSet<>();
//         for (int i = 0; i < n; i++) {
//             char c1 = s.charAt(i);
//             char c2 = t.charAt(i);
//             if (!m.containsKey(c1)) {
//                 if (visited.contains(c2)) {
//                     return false;
//                 }
//                 m.put(c1, c2);
//                 visited.add(c2);
//             } else {
//                 char c = m.get(c1);
//                 if (c != c2) {
//                     return false;
//                 }
//             }
//         }
//         return true;
//     }
// }
