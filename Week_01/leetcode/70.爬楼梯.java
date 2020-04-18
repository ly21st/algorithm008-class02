/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n ==2) {
            return 2;
        }
        int first = 1;
        int second = 2;
        int i = 3;
        int third;
        while (i <= n) {
            third = first + second;
            first = second;
            second = third;
            i++;
        }
        return second;
    }
}
// @lc code=end



// class Solution {
//     public int climbStairs(int n) {
//         int []steps = new int[n+1];
//         return climbStairsStep(0, n, steps);
//     }

//     public int climbStairsStep(int i, int n, int []steps) {
//         if (i > n) {
//             return 0;
//         }
//         if (i == n) {
//             return 1;
//         }

//         if (steps[i] > 0) {
//             return steps[i];
//         }
//         steps[i] = climbStairsStep(i+1, n, steps) + climbStairsStep(i+2, n, steps);
//         return steps[i];

//     }
// }




// class Solution {
//     public int climbStairs(int n) {
//         if (n == 1) {
//             return 1;
//         }
//         if (n ==2) {
//             return 2;
//         }
//         int []steps = new int[n+1];
//         steps[1] = 1;
//         steps[2] = 2;
//         for (int i = 3; i <= n; i++) {
//             steps[i] = steps[i-2] + steps[i-1];
//         }
//         return steps[n];
//     }
// }