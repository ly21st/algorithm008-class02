# 面试题52. 两个链表的第一个公共节点
 两个链表的第一个公共节点难度简单输入两个链表，找出它们的第一个公共节点。如下面的两个链表：![07181499d7914da51e065fc1a04e13f8.png](en-resource://database/5465:1)
在节点 c1 开始相交。&nbsp;示例 1：![15c398911ae11156ab5f182bf31808c7.png](en-resource://database/5467:1)
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

## 解法1
```
    
    class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	
	public class GetIntersectionNode {
	    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	        Set<ListNode> set = new HashSet<ListNode>();
	        while (headA != null) {
	            set.add(headA);
	            headA = headA.next;
	        }
	        while (headB != null) {
	            if (set.contains(headB)) {
	                return headB;
	            } else {
	                headB = headB.next;
	            }
	        }
	        return null;
	    }
	}
```

# 205. 同构字符串
给定两个字符串 s 和 t，判断它们是否是同构的。
如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
示例 1:
输入: s = "egg", t = "add"
输出: true

## 用数组实现
```
    
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
```

# 63. 不同路径 II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

## 状态转移方程
定义dp[i][j]为从(0,0)走到(i,j)的不同路径，

那么
* 如果(i,j)有障碍物,
dp[i][j] = 0;
* 如果(i,j)没障碍物
dp[i][j] = dp[i - 1][j] + dp[i][j - 1];

代码
```

    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            if (m < 1) {
                return 0;
            }
            int n = obstacleGrid[0].length;
            if (n < 1) {
                return 0;
            }
            int [][]dp = new int[m][n];
            if (obstacleGrid[0][0] == 0) {
                dp[0][0] = 1;
            } else {
                dp[0][0] = 0;
            }
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[0][j] == 1) {
                    dp[0][j] = 0;
                } else {
                    if (dp[0][j - 1] == 0) {
                        dp[0][j] = 0;
                    } else {
                        dp[0][j] = 1;
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    dp[i][0] = 0;
                } else {
                    if (dp[i-1][0] == 0) {
                        dp[i][0] = 0;
                    } else {
                        dp[i][0] = 1;
                    }
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
```

# 14. 最长公共前缀

编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。示例 1:输入: ["flower","flow","flight"]
输出: "fl"

##  两层循环进行求解
```

    class Solution {
        public String longestCommonPrefix(String[] strs) {
            int len = strs.length;
            if (len < 1) {
                return "";
            }
            int firstLen = strs[0].length();
            boolean endFlag = false;
            for (int j = 0; j < firstLen; j++) {
                char c = strs[0].charAt(j);
                for (int i = 1; i < len; i++) {
                    if (j >= strs[i].length()) {
                        endFlag = true;
                        break;
                    }
                    if (c != strs[i].charAt(j)) {
                        endFlag = true;
                        break;
                    }
                }
                if (endFlag) {
                    return strs[0].substring(0, j);
                }
            }
            return strs[0];
        }
    }

```

# 151. 翻转字符串里的单词

给定一个字符串，逐个翻转字符串中的每个单词。

## 类似C语言方法
```
    
    class Solution {
        public String reverseWords(String s) {
            char []arr = s.toCharArray();
            int len = arr.length;
            int k = 0; 
            int i = 0;
            int j;
            while (i < len && arr[i] == ' ') i++;
            while (len > i && arr[len-1] == ' ') len--;
            for (; i < len;) {
                if (i == 0) {
                    arr[k++] = arr[i++];
                    continue;
                }
                if (arr[i] != ' ') {
                    arr[k++] = arr[i++];
                    continue;
                }
                if (arr[i-1] == ' ') {
                    i++;
                } else {
                    arr[k++] = arr[i++];
                    continue;
                }
            }
            i = 0; 
            j = 0;
            while (j < k) {
                while (j < k && arr[j] != ' ') j++;
                reverseArr(arr, i, j);
                j++;
                i = j;
            }
            reverseArr(arr, i, j);
            reverseArr(arr, 0, k);
            return new String(Arrays.copyOfRange(arr, 0, k));
        }

        public void reverseArr(char []arr, int first, int end) {
            int i = first;
            int j = end - 1;
            char tmp;
            while ( i < j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
    }

```

## 简洁方法
```
     
    class Solution {
        public String reverseWords(String s) {
            String[] arr = s.trim().split(" +");
            Collections.reverse(Arrays.asList(arr));
            return String.join(" ", arr);
        }
    }

```



