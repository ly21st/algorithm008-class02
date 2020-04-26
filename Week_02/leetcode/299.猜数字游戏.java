package problems;

import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=299 lang=java
 *
 * [299] 猜数字游戏
 */

// @lc code=start
class Solution {
    public String getHint(String secret, String guess) {
        int secretLen = secret.length();
        int guessLen = guess.length();
        int aCount = 0;
        int bCount = 0;
        int []bucket = new int[10];

        for (int i = 0; i < secretLen; i++) {
            Character key = secret.charAt(i);
            if (key == guess.charAt(i)) {
                aCount++;
                continue;
            }
            bucket[key - '0'] += 1;
        }
        for (int i = 0; i < guessLen; i++) {
            Character key = guess.charAt(i);
            if (key == secret.charAt(i))
                continue;
            if (bucket[key - '0'] > 0) {
                bCount++;
                bucket[key - '0'] -= 1;
            }
        }
        StringBuffer sb = new StringBuffer();
        return sb.append(aCount).append("A").append(bCount).append("B").toString();
    }
}
// @lc code=end



// class Solution {
//     public String getHint(String secret, String guess) {
//         int secretLen = secret.length();
//         int guessLen = guess.length();
//         int aCount = 0;
//         int bCount = 0;
//         Map<Character, Integer> secMap = new HashMap<>();
//         for (int i = 0; i < secretLen; i++) {
//             Character key = secret.charAt(i);
//             if (key == guess.charAt(i)) {
//                 aCount++;
//                 continue;
//             }
//             Integer count;
//             if ((count = secMap.get(key)) != null) {
//                 secMap.put(key, count + 1);
//             } else {
//                 secMap.put(key, 1);
//             }
//         }
//         for (int i = 0; i < guessLen; i++) {
//             Character key = guess.charAt(i);
//             if (key == secret.charAt(i))
//                 continue;
//             Integer count = secMap.get(key);
//             if (count != null && count > 0) {
//                 bCount++;
//                 secMap.put(key, count - 1);
//             }
//         }
//         StringBuffer sb = new StringBuffer();
//         return sb.append(aCount).append("A").append(bCount).append("B").toString();
//     }
// }