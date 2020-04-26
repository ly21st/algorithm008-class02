import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=290 lang=java
 *
 * [290] 单词规律
 */

// @lc code=start
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> bucket = new HashMap<>();
        Map<String, Character> antiBucket = new HashMap<>();
        int patternLen = pattern.length();
        String []arr = str.split(" ");
        int arrLen = arr.length;
        if (patternLen != arrLen) return false;
        for (int i = 0; i < patternLen; i++) {
            Character c = pattern.charAt(i);
            String val = bucket.get(c);
            if (val == null) {
                bucket.put(c, arr[i]);
            } else {
                if (!val.equals(arr[i])) return false; 
            }

            Character c2 = antiBucket.get(arr[i]);
            if (c2 == null) {
                antiBucket.put(arr[i], c);
            } else {
                if (!c.equals(c2)) return false;
            }
        }
        return true;
    }
}
// @lc code=end



"abba"
"dog dog dog dog"
