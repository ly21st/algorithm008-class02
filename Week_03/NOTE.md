#学习笔记

> # 二叉树的前序遍历、中序遍历、后序遍历
>> ## 二叉树的前序遍历

    二叉树的前序遍历方法很多，最容易理解的几种：

>>> * 递归方法
>>> * 使用栈的循环迭代
>>> * 莫里斯遍历

>>> ### 前序遍历递归方法


```

    class Solution {

	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();
	        helper(root, res);
	        return res;
	    }
	
	    public void helper(TreeNode root, List<Integer> res) {
	        if (root == null) return;
	        res.add(root.val);
	        helper(root.left, res);
	        helper(root.right, res);
	    }
	}


```

>>> ### 前序遍历利用栈循环迭代法

```

    class Solution {
	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();
	        helper(root, res);
	        return res;
	    }
	
	    public void helper(TreeNode root, List<Integer> res) {
	        if (root == null)
	            return;
	        TreeNode node;
	        Deque<TreeNode> deque = new LinkedList<TreeNode>();
	        deque.addFirst(root);
	        while (!deque.isEmpty()) {
	            node = deque.removeFirst();
	            res.add(node.val);
	            if (node.right != null) {
	                deque.addFirst(node.right);
	            }
	            if (node.left != null) {
	                deque.addFirst(node.left);
	            }
	
	        }
	    }
    }

```

循环遍历时，通常要判断根元素（第一个元素）是在循环前入栈还是循环里面入栈，这里循环前入栈比较合适，
循环前入栈，while的写法是while (!deque.isEmpty())，如果循环里面入栈，while的写法是while(node != null || deque.isEmpty())


>>> ### 前序遍历莫里斯遍历

```

莫里斯遍历
	class Solution {
	    public List<Integer> preorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();
	        helper(root, res);
	        return res;
	    }
	
	    public void helper(TreeNode root, List<Integer> res) {
	        if (root == null)
	            return;
	        TreeNode node = root;
	        TreeNode predecessor;
	        while (node != null) {
	            if (node.left == null) {
	                res.add(node.val);
	                node = node.right;
	                continue;
	            }
	            predecessor = node.left;
	            while (predecessor.right != null && predecessor.right != node) {
	                predecessor = predecessor.right;
	            }
	            if (predecessor.right == null) {
	                res.add(node.val);
	                predecessor.right = node;
	                node = node.left;
	                continue;
	            }
	            node = node.right;
	            predecessor.right = null;
	        }
	    }
    }


```


>> ## 二叉树的中序遍历

    二叉树中序遍历最容易理解的几种：

>>> * 递归方法
>>> * 使用栈的循环迭代
>>> * 利用栈的颜色遍历
>>> * 莫里斯遍历

>>> ### 使用栈的颜色遍历


```

    class Solution {
	    public List<Integer> inorderTraversal(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();
	        if (root == null) return res;
	        Deque<Pair<Integer, TreeNode>> deque = new LinkedList<>();
	        deque.addFirst(new Pair<Integer, TreeNode>(0, root));
	        Pair<Integer, TreeNode> cur;
	        TreeNode node;
	        while (!deque.isEmpty()) {
	            cur = deque.removeFirst();
	            node = cur.getValue();
	            if (cur.getKey() == 1) {
	                res.add(node.val);
	                continue;
	            }
	            if (node.right != null) {
	                deque.addFirst(new Pair<Integer, TreeNode>(0, node.right));
	            }
	            deque.addFirst(new Pair<Integer, TreeNode>(1, node));
	            if (node.left != null) {
	                deque.addFirst(new Pair<Integer, TreeNode>(0, node.left));
	            }
	        }
	        return res;
	    }
    }

颜色遍历法其实是对以访问过的节点做一个标志，对已访问过的节点再次访问时输出，也可以把已访问的节点再次入栈时，把左右节点设置为null来判断，但会破坏原来的数据结构。也可以设置一个vistied集合，对已访问过的节点放进集合作为判断依据。

```

>> ## 二叉树的后序遍历

    二叉树后序遍历最容易理解的几种：

>>> * 递归方法
>>> * 使用栈的循环迭代(先按照根右左的方式进行遍历，最后把结果逆序得到左右根顺序的后序遍历)
>>> * 利用栈的颜色遍历


>>> ### 使用栈的循环迭代

```
    class Solution {
	    public List<Integer> postorderTraversal(TreeNode root) {
	        LinkedList<Integer> res = new LinkedList<Integer>();
	        helper(root, res);
	        return res;
	    }
	
	    public void helper(TreeNode root, LinkedList<Integer> res) {
	        if (root == null) return;
	        Deque<TreeNode> deque = new LinkedList<TreeNode>();
	        TreeNode cur = root;
	
	        while (cur != null || !deque.isEmpty()) {
	            while (cur != null) {
	                deque.addFirst(cur);
	                cur = cur.left;
	            }
	            TreeNode tmp = deque.peekFirst();
	            if (tmp.right == null) {
	                deque.removeFirst();
	                res.add(tmp.val);
	                continue;
	            }
	            cur = tmp.right;
	            tmp.right = null;
	        }  
	    }
    }

```


>> ## 孤岛问题

使用图的深度遍历，把访问过的节点标志为'0'，因此所有访问过的相连节点都会变成'0',下一次深度遍历的时候，表示找到一个新的'1',这个新的'1'与之前访问过的节点肯定不是相连的，因此深度优先遍历的次数就是孤岛的个数。


>> # 泛型递归、树的递归

> ### 递归模板


```

      public void recur(int level, int param) { 
          // terminator 

          if (level > MAX_LEVEL) { 
          // process result 
              return; 
          }
          // process current logic 
          process(level, param); 
          // drill down 
          recur( level: level + 1, newParam); 
          // restore current status 
      }
```

>> ## 爬楼梯问题

>>> #### 最简单递归解法 f(n) = f(n-2) + f(n-1)

```
    
	class Solution {
	    public int climbStairs(int n) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	        return climbStairs(n - 1) + climbStairs(n - 2);
	    }
	}

当n很大时，会发生超时现象。

```

>>> ### 带有记忆数组的递归方法

```

    class Solution {
	    public int climbStairs(int n) {
	        int []res = new int[n+1];
	        return helper(n, res);
	    }
	
	    public int helper(int n, int []res) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	
	        if (res[n] == 0)  {
	            res[n]= helper(n - 1, res) + helper(n - 2, res);
	        } 
	        return res[n];
	    }
	}

```


>>> ### 带有记忆数组的循环方法

```

	class Solution {
	    public int climbStairs(int n) {
	        int []res = new int[n+1];
	        return helper(n, res);
	    }
	
	    public int helper(int n, int []res) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	
	        res[1] = 1;
	        res[2] = 2;
	        int i = 3; 
	        while (i <= n) {
	            res[i] = res[i-2] + res[i-1];
	            i++;
	        }
	        return res[n];
	    }
	}

```

>>> ### 不保留中间结果的循环

```

	class Solution {
	    public int climbStairs(int n) {
	        return helper(n);
	    }
	
	    public int helper(int n) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	
	        int a = 1;
	        int b = 2;
	        int c; 
	        int i = 3;
	        while (i <= n) {
	            c = a + b;
	            a = b; 
	            b = c;
	            i++;
	        }
	        return b;
	    }
	}
```

>>> ### 递推法  f(i,n) = f(i+1,n) + f(i+2,n)

```

	class Solution {
	    public int climbStairs(int n) {
	        if (n == 1) return 1;
	        if (n == 2) return 2;
	        int []res = new int[n+1];
	        return helper(0, n, res);
	    }
	
	    public int helper(int i, int n, int []res) {
	        if (i > n) return 0;
	        if (i == n) return 1;
	
	        if (res[i] == 0) {
	            res[i] = helper(i+1, n, res) + helper(i+2, n, res);
	        }
	        return res[i];
	    }
	}
```

------------------------
>> ## 括号生成问题

![括号生成问题图解总览](https://pic.leetcode-cn.com/6be56bc36dd8427757cb12b814656665fd9b39d856108809d3b6344e8cf50112-image.png)


>>> * 深度优先算法容易理解，实现也简单
>>> * 广度优先算法效率较差，需要自己维护队列，定义队列元素结构
>>> * 动态规划算法有两种实现，为了优化都需要存储中间状态：
>>>> * 一种是自顶向下，范围逐渐缩小，f(n）->f(n-1)->f(n-2)...f(1)->f(0)
>>>> * 一种是自底向上，范围逐渐扩大, f(0)->f(1)->...->f(n)


------------------------
>> ## 二叉树的最近公共祖先问题

>>> #### 暴力解法1

求出根节点到p所经过的所有路径，求出根节点到q所经过的所有路径，再对两条路径两层循环，求公共节点。


>>> #### 暴力解法2

求出根节点到p所经过的所有路径p1，从下往上遍历p1,寻找从经过的节点出发，是否能找到另一个节点q。


>>> ### 基于节点条件递归深度遍历 

深度遍历每一个节点，左子树，右子树，节点本身三个之中任意两个含有p与q，那么这个节点就是公共节点。

>>> ### 有父指针的迭代

>>> ### 无父子针的迭代



>> ## 从前序和中序遍历序列构造二叉树

思路不难想到，把问题分解为多个子问题进行求解，但边界问题很容易混淆。
原来的思路和每次要求出前序遍历的子集，实时上不用，初始的前序遍历的顺序就是深度遍历每一棵子树的根节点的顺序。

```

		class Solution {
		    int preIndex = 0;
		    Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		    public TreeNode buildTree(int[] preorder, int[] inorder) {
		        int len1= preorder.length;
		        int len2 = inorder.length;
		        if (len1 < 1 || len2 < 1 || len1 != len2) return null;
		        for (int i = 0; i < len1; i++) {
		            indexMap.put(inorder[i], i);
		        }
		        return helper(preorder, inorder, 0, len1);
		    }
		
		    public TreeNode helper(int[] preorder, int[] inorder, int leftIndex, int rightIndex) {
		        if (leftIndex == rightIndex) return null;
		        TreeNode root = new TreeNode(preorder[preIndex]);
		        int inorderMid;
		        inorderMid = indexMap.get(preorder[preIndex]);
		        preIndex++;
		        root.left = helper(preorder, inorder, leftIndex, inorderMid);
		        root.right = helper(preorder, inorder, inorderMid + 1, rightIndex);
		        return root;
		    }
		}
```

------
>> ## 组合问题 
>给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。


>>> #### 回溯法

```

	class Solution {
	    public List<List<Integer>> combine(int n, int k) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        int []arr = new int[k];
	        helper(1, n, k, res, arr, 0);
	        return res;
	    }
	
	    public void helper(int b, int n, int k, List<List<Integer>> res, int[] arr, int i) {
	        if (i == k) {
	            List<Integer> oneRes = new ArrayList<>();
	            for (int v : arr) {
	                oneRes.add(v);
	            }
	            res.add(oneRes);
	            return;
	        }
	        for (int j = b; j <= n; j++) {  
	            arr[i] = j;
	            helper(j + 1, n, k, res, arr, i + 1);
	        }
	    }
	}
```


>>> ### 对上一版本的优化
>考虑剩余空间，最大能放的值，提前剪枝

```

	class Solution {
	    public List<List<Integer>> combine(int n, int k) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        int []arr = new int[k];
	        helper(1, n, k, res, arr, 0);
	        return res;
	    }
	
	    public void helper(int b, int n, int k, List<List<Integer>> res, int[] arr, int i) {
	        if (i == k) {
	            List<Integer> oneRes = new ArrayList<>();
	            for (int v : arr) {
	                oneRes.add(v);
	            }
	            res.add(oneRes);
	            return;
	        }
	        for (int j = b; j <= n - (k - i) + 1; j++) {  
	            arr[i] = j;
	            helper(j + 1, n, k, res, arr, i + 1);
	        }
	    }
	}


```

--------

>> ## 全排序问题


分为两种情况，

* 不含重复元素

```

	class Solution {
	    public List<List<Integer>> permute(int[] nums) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        int len = nums.length;
	        if (len < 1) return res;
	        Deque<Integer> deque = new LinkedList<Integer>();
	        boolean []used = new boolean[len];
	        helper(nums, len, res, deque, used);
	        return res;
	    }
	
	    public void helper(int[] nums, int n, List<List<Integer>> res, Deque<Integer> deque, boolean[] used) {
	        if (deque.size() == n) {
	            List<Integer> list = new ArrayList<Integer>(deque);
	            res.add(list);
	            return;
	        }
	        for (int i = 0; i < n; i++) {
	            if (used[i]) continue;
	            used[i] = true;
	            deque.addLast(nums[i]);
	            helper(nums, n, res, deque, used);
	            used[i] = false;
	            deque.removeLast();
	        }
	    }
	}
```

* 含有重复元素

```

	class Solution {
	    public List<List<Integer>> permuteUnique(int[] nums) {
	        int len = nums.length;
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        Deque<Integer> output = new LinkedList<Integer>();
	        boolean []used = new boolean[len];
	        Arrays.sort(nums);
	        helper(nums, len, res, output, used);
	        return res;
	    }
	
	    public void helper(int[] nums, int n, List<List<Integer>> res, Deque<Integer> output, boolean[] used) {
	        if (output.size() == n) {
	            List<Integer> list = new ArrayList<Integer>(output);
	            res.add(list);
	            return;
	        }
	
	        for (int i = 0; i < n; i++) {
	            if (used[i]) continue;
	            if (i > 0 && nums[i] == nums[i-1] && used[i-1] == false) continue;
	            used[i] = true;
	            output.addLast(nums[i]);
	            helper(nums, n, res, output, used);
	            used[i] = false;
	            output.removeLast();
	        }
	    }
	}

```

含有重复元素时，首先排序，使得相同元素连在一起。选择元素的时候，判断该元素是否重复，并且被使用多次，根据这个条件进行剪枝。


>>> ### C++使用stl简洁解法

```

	class Solution {
	public:
	    vector<vector<int>> permuteUnique(vector<int>& nums) {
	        vector<vector<int>> ans;
	        sort(nums.begin(), nums.end());
	        ans.emplace_back(nums);
	        while (next_permutation(nums.begin(), nums.end()))
	            ans.emplace_back(nums);
	
	        return ans;
	    }
	};

```

----

>> ## 翻转一颗二叉树

使用递归方法容易实现。还可以使用广度优先队列求解，入队前交换左右节点。

>> ## 最小基因变化

```

	class Solution {
	    int min = Integer.MAX_VALUE;
	    public int minMutation(String start, String end, String[] bank) {
	        
	        Set<String> visited =new  HashSet<String>();
	        helper(start, end, bank, visited, 0);
	        return min == Integer.MAX_VALUE? -1 : min;
	    }
	
	    public void helper(String cur, String end, String[] bank, Set<String> visited, int count) {
	        if (cur.equals(end)) {
	            if (count < min) {
	                min = count;
	            }
	            return;
	        }
	        if (count >= min) return;
	        for (String s : bank) {
	            int diff = 0;
	            int len = s.length();
	            for (int i = 0; i < len; i++) {
	                if (s.charAt(i) != cur.charAt(i)) diff++;
	                if (diff > 1) break;
	            }
	            if (diff == 1 && !visited.contains(s)) {
	                visited.add(s);
	                helper(s, end, bank, visited, count + 1);
	                visited.remove(s);
	            }
	        }
	    }
	}
```

这里使用了深度优先算法，其实跟单个字符取什么值没有关系，关键是只要替换一个值，能在基因库找到或找不到。
基因库的值就是候选值，对候选值进行回溯尝试，终止条件是找到目标值，或候选值全部访问过了也找不到。

这里还可以使用广度优先队列方法求解。使用双重循环。外层循环是队列是否为空，内层循环是只改变一个字符，队列中的元素进行循环。


>> ## 求二叉树的最大深度

>> * 方法1
>
```

	class Solution {
	    int max = 1;
	    public int maxDepth(TreeNode root) {
	        if (root == null) return 0;
	        helper(root, 1);
	        return max;
	    }
	
	    public void helper(TreeNode root, int curDept) {
	        if (root.left == null && root.right == null) return;
	        curDept += 1;
	        if (curDept > max) max = curDept;
	        if (root.left != null) helper(root.left, curDept);
	        if (root.right != null) helper(root.right, curDept);
	    }
	}
```

>> * 方法2

```

	class Solution {
	    public int maxDepth(TreeNode root) {
	        if (root == null) return 0;
	        return helper(root);
	    }
	
	    public int helper(TreeNode root) {
	        if (root == null) return 0;
	        int leftDept = helper(root.left);
	        int rightDept = helper(root.right);
	        return Math.max(leftDept, rightDept) + 1;
	    }
	}
```


>> ## 二叉树的序列化与反序列化

>> * 使用完全二叉树的方法，使用数组作为底层数据结构，与堆的构造类似，该方法执行到47/48个测试例子时显示内存超出范围。

>> * 使用前序遍历方式，把访问过的节点记录下来序列化；反序列化也是同样的方式。这里用了一个重要的数据结构是LinkedList,可以双向插入，删除。

```

	public class Codec {
	
	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	        if (root == null) return "null,";
	        String s = serializeDfs(root);
	    //    System.out.println("s:" + s);
	        return s;
	    }
	
	    public String serializeDfs(TreeNode root) {
	        if (root == null) return "null,";
	        String s;
	        s = String.valueOf(root.val) + ",";
	        s += serializeDfs(root.left);
	        s += serializeDfs(root.right);
	        return s;
	    }
	
	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	        String []arr = data.split(",");
	        LinkedList<String> list = new LinkedList<String>(Arrays.asList(arr));
	        return deserializeDfs(list);
	    }
	
	    public TreeNode deserializeDfs(LinkedList<String> list) {
	        if (list.isEmpty()) return null;
	        String s = list.removeFirst();
	        if (s.equals("null")) return null;
	        TreeNode node = new TreeNode(Integer.valueOf(s));
	        node.left = deserializeDfs(list);
	        node.right = deserializeDfs(list);
	        return node;
	    }
	}
```


> # 分治和回溯

分治和回溯就是递归，分治把一个大问题分解为很多个小问题，找重复性。最后合并。

分治代码模板：

```
	
	def divide_conquer(problem, param1, param2, ...): 
	  # recursion terminator 
	  if problem is None: 
		print_result 
		return 
	
	  # prepare data 
	  data = prepare_data(problem) 
	  subproblems = split_problem(problem, data) 
	
	  # conquer subproblems 
	  subresult1 = self.divide_conquer(subproblems[0], p1, ...) 
	  subresult2 = self.divide_conquer(subproblems[1], p1, ...) 
	  subresult3 = self.divide_conquer(subproblems[2], p1, ...) 
	  …
	
	  # process and generate the final result 
	  result = process_result(subresult1, subresult2, subresult3, …)
		
	  # revert the current level states


```

>> ## pow(x,n)问题

>> * 暴力法
这种解法会超时

>> * pow(x,n) = pow(x, n/2) * pow(x, n/2) 或  pow(x,n) = pow(x, n/2) * pow(x, n/2) * x

```

	class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n > 0) return helper(x, n);
        else return 1 / helper(x, -n);
    }

    public double helper(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            double s = helper(x, n / 2);
            return s * s;
        } else {
            double s = helper(x, n / 2);
            return s * s * x;
        }
    }
}
```


>> ## 子集问题

>> * 方法一：递归
思路

开始假设输出子集为空，每一步都向子集添加新的整数，并生成新的子集。

>> * 方法二：回溯
算法

幂集是所有长度从 0 到 n 所有子集的组合。

根据定义，该问题可以看作是从序列中生成幂集。

遍历 子集长度，通过 回溯 生成所有给定长度的子集

>> * 方法三：字典排序（二进制排序） 子集
思路

该方法思路来自于 Donald E. Knuth。

将每个子集映射到长度为 n 的位掩码中，其中第 i 位掩码 nums[i] 为 1，表示第 i 个元素在子集中；如果第 i 位掩码 nums[i] 为 0，表示第 i 个元素不在子集中。

前两种方法都能想到，第三种方法很难想到。

```

	class Solution {
	    public List<List<Integer>> subsets(int[] nums) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        int n = nums.length;
	        for (int i = (int) Math.pow(2, n); i < (int)Math.pow(2, n + 1); i++) {
	            String s = Integer.toBinaryString(i).substring(1);
	
	            List<Integer> list = new ArrayList<Integer>();
	            for (int j = 0; j < n; j++) {
	                if (s.charAt(j) == '1') list.add(nums[j]);
	            }
	            res.add(list);
	        }
	        return res;
	    }
	}
```

>> ## n皇后问题

最直观的方法是使用回溯法求解，这里也是使用回溯法，还有用位运算求解的，这个后面再论述。

n皇后问题其他逻辑是比较清晰的，比较容易找规律，边界条件也容易把握。

```

	class Solution {

	    public List<List<String>> solveNQueens(int n) {
	        int [][]arr = new int[n][n];
	        int []cols = new int[n];
	        int []m1 = new int[2 * n - 1];
	        int []m2 = new int[2 * n - 1];
	        List<List<String>> res = new ArrayList<List<String>>();
	        dfs(n, 0, arr, cols,  m1, m2, res);
	        return res;
	    }
	
	    public void dfs(int n, int cur, int [][]arr, int []cols, 
	                    int []m1, int []m2, List<List<String>> res) {
	        if (cur == n) {
	            List<String> list = new ArrayList<String>();
	            for (int i = 0; i < n; i++) {
	                String s = "";
	                for (int j = 0; j < n; j++) {
	                    if (arr[i][j] == 1) s += "Q";
	                    else s += ".";
	                }
	                list.add(s);
	            }
	            res.add(list);
	            return;
	        }
	
	        for (int j = 0; j < n; j++) {
	            if (cols[j] != 1 && m1[cur + j] != 1 && m2[n-1+(cur-j)] != 1) {
	                arr[cur][j] = 1;
	                cols[j] = 1;
	                m1[cur + j] = 1;
	                m2[n - 1 + cur - j] = 1;
	                dfs(n, cur+1, arr, cols, m1, m2, res);
	                arr[cur][j] = 0;
	                cols[j] = 0;
	                m1[cur + j] = 0;
	                m2[n - 1 + cur - j] = 0;
	            }
	        }
	    }
	}
```
