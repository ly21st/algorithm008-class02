import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;

/*
 * @lc app=leetcode.cn id=73 lang=java
 *
 * [73] 矩阵置零
 */

// @lc code=start
class Solution {
    public void setZeroes(int[][] matrix) {
        List<int[]> zeroArray = new ArrayList<int[]>();
        int rows = matrix.length;
        if (rows < 1) return;
        int cols = matrix[0].length;
        if (cols < 1) return;
        int []linesArr = new int[rows];
        int []colsArr = new int[cols];

        for (int  i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    linesArr[i] = 1;
                    colsArr[j] = 1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            if (linesArr[i] != 0) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < cols; j++) {
            if (colsArr[j] != 0) {
                for (int i = 0; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
// @lc code=end



// 暴力法
// class Solution {
//     public void setZeroes(int[][] matrix) {
//         List<Pair<Integer, Integer>> zeroArray = new ArrayList<Pair<Integer, Integer>>();
//         int rows = matrix.length;
//         if (rows < 1) return;
//         int cols = matrix[0].length;
//         if (cols < 1) return;
//         for (int  i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == 0) {
//                     zeroArray.add(new Pair<Integer, Integer>(i, j));
//                 }
//             }
//         }

//         int zeroArrLen = zeroArray.size();
//         for (int k = 0; k < zeroArrLen; k++) {
//             Pair<Integer, Integer> elem = zeroArray.get(k);
//             int i = elem.getKey();
//             int j = elem.getValue();
//             for (int ii = 0; ii < cols; ii++) {
//                 matrix[i][ii] = 0;
//             }
//             for (int ii = 0; ii < rows; ii++) {
//                 matrix[ii][j] = 0;
//             }
//         }
//     }
// }



// 优化的暴力法,优化1
// class Solution {
//     public void setZeroes(int[][] matrix) {
//         List<Pair<Integer, Integer>> zeroArray = new ArrayList<Pair<Integer, Integer>>();
//         int rows = matrix.length;
//         if (rows < 1) return;
//         int cols = matrix[0].length;
//         if (cols < 1) return;
//         for (int  i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == 0) {
//                     zeroArray.add(new Pair<Integer, Integer>(i, j));
//                 }
//             }
//         }

//         int zeroArrLen = zeroArray.size();
//         Set<Integer> linesSet = new HashSet<Integer>(zeroArrLen);
//         Set<Integer> colsSet = new HashSet<Integer>(zeroArrLen);
//         for (int k = 0; k < zeroArrLen; k++) {
//             Pair<Integer, Integer> elem = zeroArray.get(k);
//             int i = elem.getKey();
//             int j = elem.getValue();
//             if (!linesSet.contains(i)) {
//                 for (int ii = 0; ii < cols; ii++) {
//                     matrix[i][ii] = 0;
//                 } 
//                 linesSet.add(i);
//             }
//             if (!colsSet.contains(j)) {
//                 for (int ii = 0; ii < rows; ii++) {
//                     matrix[ii][j] = 0;
//                 }
//                 colsSet.add(j);
//             }
//         }
//     }
// }



// 优化的暴力法，优化2
// class Solution {
//     public void setZeroes(int[][] matrix) {
//         List<int[]> zeroArray = new ArrayList<int[]>();
//         int rows = matrix.length;
//         if (rows < 1) return;
//         int cols = matrix[0].length;
//         if (cols < 1) return;
//         for (int  i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 if (matrix[i][j] == 0) {
//                     zeroArray.add(new int[]{i, j});
//                 }
//             }
//         }

//         int zeroArrLen = zeroArray.size();
//         Set<Integer> linesSet = new HashSet<Integer>(zeroArrLen);
//         Set<Integer> colsSet = new HashSet<Integer>(zeroArrLen);
//         for (int k = 0; k < zeroArrLen; k++) {
//             int []elem = zeroArray.get(k);
//             int i = elem[0];
//             int j = elem[1];
//             if (!linesSet.contains(i)) {
//                 for (int ii = 0; ii < cols; ii++) {
//                     matrix[i][ii] = 0;
//                 } 
//                 linesSet.add(i);
//             }
//             if (!colsSet.contains(j)) {
//                 for (int ii = 0; ii < rows; ii++) {
//                     matrix[ii][j] = 0;
//                 }
//                 colsSet.add(j);
//             }
//         }
//     }
// }