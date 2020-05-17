# 学习笔记
----

>> ## 二叉树的层次遍历

每一层里面的所有元素放进一个list，最后结果是List<List<Intger>>

这里涉及到什么时候把一层的元素放进一个list，至少有两个时刻，一个是把父节点的所有子节点插入队列时，同时输出一层的结果。

另一个可能是同一层的元素出队列时，输出一层的结果。

怎么表示同一层的所有元素？可以把同一层的所有元素放进一个队列，多个层次就有多个队列。输出多层树时就会产生两个循环，
最外层循环是while(!deque.isEmpty()),里面一层循环是一层的元素非空。这种方法占用内存稍多。

另一种方法是，构造一个新的数据结构，包含两个数据元素,分别为TreeNode与level，每次插入一个TreeNode都标志是第一层，这种方式使用一个
循环就可以了，也不用每层用一个单独的list。

这里展示我首先想到的一个解法，这里采用的是一个层级一个队列的方法。

```

	class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<List<Integer>> res) {
        if (root == null) return;
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        
        deque.addLast(root);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(root.val);
        res.add(list);
        TreeNode node;
        while (!deque.isEmpty()) {
            Deque<TreeNode> levelDeque = new LinkedList<TreeNode>();
            list = new ArrayList<Integer>();
            while (!deque.isEmpty()) {
                node = deque.removeFirst();
                if (node.left == null && node.right == null) continue;
                if (node.left != null) {
                    list.add(node.left.val);
                    levelDeque.addLast(node.left);
                }
                if (node.right != null) {
                    list.add(node.right.val);
                    levelDeque.addLast(node.right);
                }  
            }
            if (!list.isEmpty()) res.add(list);
            deque = levelDeque;
        }
    }
}

```

>>> ### 深度优先遍历方法

这道题竟然还可以用深度优先遍历方法进行求解，使用额外参数level，表示当前层数，由于每层结果得到一个list，因此，第一次访问一个新的层次时，产生一个List对象，由于结果中的List不会减少，所以只会第一次到达一个新的层时会产生List。

深度优先的算法大多数情况下比广度优先算法效率更高，原因是广度优先算法需要自己维护一个队列，深度优先算法维护一个栈，但这个栈可以不用自己维护，可以使用系统天然函数调用产生的栈。


>> ## DFS

```

	递归写法
	自动检测
	visited = set() 
	
	def dfs(node, visited):
	    if node in visited: # terminator
	    	# already visited 
	    	return 
	
		visited.add(node) 
	
		# process current node here. 
		...
		for next_node in node.children(): 
			if next_node not in visited: 
				dfs(next_node, visited)
	
	
	非递归写法
	自动检测
	def DFS(self, tree): 
	
		if tree.root is None: 
			return [] 
	
		visited, stack = [], [tree.root]
	
		while stack: 
			node = stack.pop() 
			visited.add(node)
	
			process (node) 
			nodes = generate_related_nodes(node) 
			stack.push(nodes) 
	
		# other processing work 
		...
```


>> ## BFS

```

	自动检测
	def BFS(graph, start, end):
	    visited = set()
		queue = [] 
		queue.append([start]) 
	
		while queue: 
			node = queue.pop() 
			visited.add(node)
	
			process(node) 
			nodes = generate_related_nodes(node) 
			queue.push(nodes)
	
		# other processing work 
```

>> ## 贪心算法
贪心算法是一种在每一步选择中都采取在当前状态下最好或最优的选择，从而希望导致结果是全局最好或最优的算法。

贪心算法与动态规划不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。


>> ## 二分查找的前提

1. 目标函数的单调性
2. 存在上下界
3. 能够通过索引访问


>> ## 45. 跳跃游戏 II

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

>>> 从后往前贪心法

从后往前查找，找到索引最小的能到达最后元素的索引，然后更新最后索引，再次找满足条件的最小索引，
时间复杂度为O(n*n)

>>> 从前往后贪心法

```

	public int jump(int[] nums) {
	    int end = 0;
	    int maxPosition = 0; 
	    int steps = 0;
	    for(int i = 0; i < nums.length - 1; i++){
	        //找能跳的最远的
	        maxPosition = Math.max(maxPosition, nums[i] + i); 
	        if( i == end){ //遇到边界，就更新边界，并且步数加一
	            end = maxPosition;
	            steps++;
	        }
	    }
	    return steps;
	}

```

这个官方解法，有点不好理解。
关键在于什么时候步数增加1.记住“产生新的边界时步数增加1”，这样就比较好理解了。
当开始，索引为0时，会产生新的边界，因此当i索引为0时，步数增加1.

>>> 参考官方解法，我的实现,比官方解法好理解

```

	class Solution {
	    int count = 0;
	    public int jump(int[] nums) {
	        int len = nums.length;
	        int i = 0; 
	        int maxPosition = 0;
	        while (i < len - 1) {
	            if (i + nums[i] >= len - 1 ) {
	                count++;
	                break;
	            }
	            int maxi = i + 1;
	            for (int j = i + 1; j <= i + nums[i]; j++) {
	                if (j + nums[j] > maxPosition) {
	                    maxPosition = j + nums[j];
	                    maxi = j;
	                }
	            }
	            i = maxi;
	            count++;
	        }
	        return count;
	    }
	}
```

“每次做出选择时步数加1”。每次什么时候做出选择？由于每次达到边界时，该位置索引对应的值是能走的最大步数，索引可能的选择有1..nums[i],
也就是下一步最大的位置可能为i+1+nums[i+1],i+2+nums[i+2],i+3+nums[i+3]...i+nums[i]+nums[i+nums[i]],从这里面选择最大的位置
maxPosition,找到对应的索引maxi,更新i索引i=maxi,进行下一轮查找。


>> ## 单词接龙

127给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。

>>> ### 思路
>
刚看到这个题目，就感觉会有多种解法，我首先用的是习惯的深度优先解法，几个深度优先算法都超时。
然后看了官方解法，官方解法是广度优先解法。深度优先算法会超时的原因，我认为是用了系统栈进行递归，需要全部进行遍历一遍才能返回结果，因此会超时。而广度优先算法只要找到一个就可以返回，因此广度优先时间上占优。

```

	class Solution {
	    int L;
	    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	        Set<String> visited = new HashSet<String>();
	        Map<String, List<String>> map = new HashMap<String, List<String>>();
	        L = beginWord.length();
	        for (String s : wordList) {
	            for (int i = 0; i < L; i++) {
	                String newStr = s.substring(0, i) + '*' + s.substring(i + 1, L);
	                List<String> list = map.getOrDefault(newStr, new ArrayList<String>());
	                list.add(s);
	                map.put(newStr, list);
	            }
	        }
	        return helper(beginWord, endWord, map, visited);
	    }
	
	    public int helper(String beginWord, String endWord, Map<String, List<String>> map, 
	                    Set<String> visited) {
	        Deque<Pair<String, Integer>> deque = new LinkedList<Pair<String, Integer>>();
	        deque.addLast(new Pair<String, Integer>(beginWord, 0));
	        while (!deque.isEmpty()) {
	            Pair<String, Integer> item = deque.removeFirst();
	            String key = item.getKey();
	            Integer val = item.getValue();
	            if (key.equals(endWord)) {
	                return val + 1;
	            }
	            for (int i = 0; i < L; i++) {
	                String newStr = key.substring(0, i) + "*" + key.substring(i+1, L);
	                List<String> list = map.getOrDefault(newStr, new ArrayList<String>());
	                for (String s : list) {
	                    if (!visited.contains(s)) {
	                        visited.add(s);
	                        deque.addLast(new Pair<String, Integer>(s, val + 1));
	                    }
	                }
	            }
	        }   
	        return 0;
	    }
	}
```

值得注意的是层数的判断，可以在进入队列前判断，也可以在出队列时判断，进入队列前判断，第一个入队列元素层级为1；出队列时判断，第一个进队列元素层级为0。我的解法是出队列时判断，官方解法是入队列时判断，官方时间稍微好一点。

>>> ### 比较好的解法

更好的解法是采用从前往后，从后往前双向BFS方法。

```

	class Solution {
	
	  private int L;
	  private Map<String, List<String>> allComboDict;
	
	  Solution() {
	    this.L = 0;
	
	    // Dictionary to hold combination of words that can be formed,
	    // from any given word. By changing one letter at a time.
	    this.allComboDict = new HashMap<>();
	  }
	
	  private int visitWordNode(
	      Queue<Pair<String, Integer>> Q,
	      Map<String, Integer> visited,
	      Map<String, Integer> othersVisited) {
	
	    Pair<String, Integer> node = Q.remove();
	    String word = node.getKey();
	    int level = node.getValue();
	
	    for (int i = 0; i < this.L; i++) {
	
	      // Intermediate words for current word
	      String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
	
	      // Next states are all the words which share the same intermediate state.
	      for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
	        // If at any point if we find what we are looking for
	        // i.e. the end word - we can return with the answer.
	        if (othersVisited.containsKey(adjacentWord)) {
	          return level + othersVisited.get(adjacentWord);
	        }
	
	        if (!visited.containsKey(adjacentWord)) {
	
	          // Save the level as the value of the dictionary, to save number of hops.
	          visited.put(adjacentWord, level + 1);
	          Q.add(new Pair(adjacentWord, level + 1));
	        }
	      }
	    }
	    return -1;
	  }
	
	  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	
	    if (!wordList.contains(endWord)) {
	      return 0;
	    }
	
	    // Since all words are of same length.
	    this.L = beginWord.length();
	
	    wordList.forEach(
	        word -> {
	          for (int i = 0; i < L; i++) {
	            // Key is the generic word
	            // Value is a list of words which have the same intermediate generic word.
	            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
	            List<String> transformations =
	                this.allComboDict.getOrDefault(newWord, new ArrayList<>());
	            transformations.add(word);
	            this.allComboDict.put(newWord, transformations);
	          }
	        });
	
	    // Queues for birdirectional BFS
	    // BFS starting from beginWord
	    Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
	    // BFS starting from endWord
	    Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
	    Q_begin.add(new Pair(beginWord, 1));
	    Q_end.add(new Pair(endWord, 1));
	
	    // Visited to make sure we don't repeat processing same word.
	    Map<String, Integer> visitedBegin = new HashMap<>();
	    Map<String, Integer> visitedEnd = new HashMap<>();
	    visitedBegin.put(beginWord, 1);
	    visitedEnd.put(endWord, 1);
	
	    while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
	
	      // One hop from begin word
	      int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
	      if (ans > -1) {
	        return ans;
	      }
	
	      // One hop from end word
	      ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
	      if (ans > -1) {
	        return ans;
	      }
	    }
	
	    return 0;
	  }
	}

```


>> ## 岛屿数量

200.给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。


>>> ### 思路

其实就是求深度优先算法与广度优先算法的次数。
```

	class Solution {
	    int count = 0; 
	    public int numIslands(char[][] grid) {
	        int m = grid.length;
	        if (m < 1) return 0; 
	        int n = grid[0].length;
	        if (n < 1) return 0;
	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                if (grid[i][j] == '1') {
	                    count++;
	                    dfs(grid, m, n, i, j);
	                }
	            }
	        }
	        return count;
	    }
	
	    public void dfs(char[][]grid, int m, int n, int i, int j) {
	        if (grid[i][j] == '0') {
	            return;
	        }
	        grid[i][j] = '0';
	        if (i - 1 >= 0) dfs(grid, m, n, i - 1, j);
	        if (i + 1 < m) dfs(grid, m, n, i + 1, j);
	        if (j - 1 >= 0) dfs(grid, m, n, i, j - 1);
	        if (j + 1 < n) dfs(grid, m, n, i, j + 1);
	    }
	}

```


>> ## 柠檬水找钱


860.在柠檬水摊上，每一杯柠檬水的售价为 5 美元。

顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。

每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

注意，一开始你手头没有任何零钱。

如果你能给每位顾客正确找零，返回 true ，否则返回 false 

>>> ### 解题思路
>
>每次优先找面值的钱，这里是面值10，不能找就返回false。最后返回true。

```

	class Solution {
	    public boolean lemonadeChange(int[] bills) {
	        int five = 0;
	        int ten = 0;
	        for (int bill : bills) {
	            if (bill == 5) {   
	                five++;
	                continue;
	            }
	            if (bill == 10) {
	                ten++;
	                if (five < 1) return false;
	                five--;
	                continue;
	            }
	            if (bill == 20) {
	                int left = 15;
	                if (ten >= 1) {
	                    ten--;
	                    left = 5;
	                }
	                if (five >= left / 5) {
	                    five -= left / 5;
	                } else {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	}
```


>> ## 使用二分查找第一个无序的地方

寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

```

	public class FindFirstUnorderNum {
	    public int findFirstNum(int []nums) {
	        int len = nums.length;
	        if (len <= 2) return len - 1;
	        int i = 0;  
	        int j = len - 1;
	        int mid;
	        System.out.println("111");
	        while ( j - i > 2) {
	            mid = i + (j - i) / 2;
	            System.out.println("mid:" + mid + " =" + nums[mid]);
	            // 左边有序
	            if (nums[i] <= nums[mid]) {
	                // 右边个数大于3，继续缩小范围
	                if (j - mid > 1) {
	                    i = mid;
	                } else {
	                    return nums[j];
	                }
	            // 右边有序    
	            } else {
	                // 左边个数大于3，缩小范围
	                if (mid - i > 1) {
	                     j = mid;
	                } else {
	                    return nums[mid];
	                }
	            }
	        }
	        return nums[j];
	    }
	}
```


