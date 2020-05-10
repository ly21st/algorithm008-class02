package problems-0003;

import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=415 lang=java
 *
 * [415] 字符串相加
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        char []arr;
        int t;
        if (len1 >= len2) {
            arr = num1.toCharArray();
            t = len1 - 1;
        } else {
            arr = num2.toCharArray();
            t = len2 - 1;
        }
        int j1 = len1 - 1;
        int j2 = len2 - 1;
        boolean addFlag = false;
        for (; j1 >= 0 && j2 >= 0; j1--, j2--) {
            char c1 = num1.charAt(j1);
            char c2 = num2.charAt(j2);
            int n;
            if (addFlag) {
                n = c1 - '0' + c2 - '0' + 1;
            } else {
                n = c1 - '0' + c2 - '0';
            }
            
            if (n > 9) {
                arr[t--] = (char)(n - 10 + '0');
                addFlag = true;
            }
            else {
                arr[t--] = (char)(n + '0');
                addFlag = false;
            }
        }

        for (; t>= 0; t--) {
            if (arr[t] == '9') {
                if (addFlag) {
                    arr[t] = '0';
                    addFlag = true;
                } else {
                    addFlag = false;
                    break;
                }
            } else {
                if (addFlag) {
                    arr[t] += 1;
                }
                addFlag = false;
                break;
            }
        }
        StringBuffer str = new StringBuffer();
        if (t < 0 && addFlag) {
            return "1" + String.valueOf(arr);
        }
        return String.valueOf(arr);
    }
}
// @lc code=end


