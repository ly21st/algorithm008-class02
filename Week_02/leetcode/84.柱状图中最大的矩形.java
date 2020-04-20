import java.util.Stack;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) return 0;
        int len = heights.length;
        if (len < 1) return 0;
        Stack<Integer>s = new Stack<>();
        s.push(-1);
        int area = 0;
        for (int i = 0; i < len; i++) {
            while (s.peek() != -1 && heights[s.peek()] > heights[i]) {
                int oldTop = s.pop();
                area = Math.max(area, (i - s.peek() - 1) * heights[oldTop]);
            }
            s.push(i);
        }
        while (s.peek() != -1) {
            int oldTop = s.pop();
            area = Math.max(area, (len - s.peek() - 1) * heights[oldTop]);
        }
        return area;
    }
}
// @lc code=end



// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         if (heights == null) return 0;
//         int len = heights.length;
//         if (len < 1) return 0;
//         return segmentLargestRectangleArea(heights, 0, len - 1);
//     }

//     public int segmentLargestRectangleArea(int[] heights, int i, int j) {
//         if ( i > j) return 0;
//         int area = 0;
//         int minIndex = i;
//         int minHeight = heights[i];
//         for (int k = i; k <= j; k++) {
//             if (heights[k] < minHeight) {
//                 minHeight = heights[k];
//                 minIndex = k;
//             }
//         }
//         area = minHeight * (j - i + 1);
//         area = Math.max(area, segmentLargestRectangleArea(heights, i, minIndex - 1));
//         area = Math.max(area, segmentLargestRectangleArea(heights, minIndex + 1, j));
//         return area;
//     }
// }