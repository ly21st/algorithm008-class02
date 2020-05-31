# 学习笔记

## 打家劫舍

```
	
	 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
	 * 
	 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
	 * 
	 * 示例 1:
	 * 
	 * 输入: [1,2,3,1]
	 * 输出: 4
	 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
	 * 偷窃到的最高金额 = 1 + 3 = 4 。
	 * 
	 * 示例 2:
	 * 
	 * 输入: [2,7,9,3,1]
	 * 输出: 12
	 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
	 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。

```

>> 回溯法求解

尝试用回溯法进行求解。
```
	class Solution {
	    int max = 0; 
	    public int rob(int[] nums) {
	        int len = nums.length;
	        if (len < 1) return 0;
	        helper(nums, len, 0, 0);
	        return max;
	    }
	
	    public void helper(int[] nums, int len, int curCnt, int first) {
	        if (first >= len) {
	            if (curCnt > max) {
	                max = curCnt;
	                return;
	            }
	        }
	        for (int i = first; i < len; i++) {
	            helper(nums, len, curCnt + nums[i], i + 2);
	        }
	    }
	}
```

>> ## 动态规划法

用max[i]表示最后一个房间位i时，能偷窃到的最大金额。
那么，max[i] = max(max[i - 3], max[i - 2]) + nums[i],
最后返回max[len - 2]与max[len - 1]中的较大值即可。

```

	class Solution {
	    public int rob(int[] nums) {
	        int len = nums.length;
	        if (len < 1) return 0;
	        if (len < 2) return nums[0];
	        if (len < 3) {
	            return nums[0] >= nums[1]? nums[0] : nums[1];
	        } 
	        int []max = new int[len];
	        max[0] = nums[0];
	        max[1] = nums[1];
	        for (int i = 2; i < len; i++) {
	            if (i == 2) {
	                max[i] = max[i - 2] + nums[i];
	            } else {
	                max[i] = Math.max(max[i - 3], max[i - 2]) + nums[i];
	            }
	        }
	        return max[len - 2] > max[len - 1]? max[len - 2] : max[len - 1]; 
	    }
	}
```

----------

# 动态规划

## 关键点
* 动态规划和递归或者分治没有根本上的区别（关键看有无最优的子结构）
* 共性： 找到重复子问题
* 差异性: 最优子结构、中途可以淘汰次优解


## 动态规划小结

1. 打破自己的思维惯性，形成机器思维。
2. 理解复杂逻辑的关键。
3. 也是职业进阶的要点要领。

## 五步解决动态规划
1. 定义子问题
2. 猜（部分子问题）
3. 相关子问题解决
4. 递归&记忆化数组或者建立dp表（自顶向下或自底向上）
5. 解决原始问题。

## 动态规划三件套
1. 定义状态
2. 动态转移方程
3. 初始状态和边界条件。

## 股票问题
股票问题有6道经典题目，有限制一次交易，限制两次交易，不限交易，最多k次交易，

可以认为通用情况是最多k次交易，其他情况是特例。

可以用状态机的技巧来解决6道问题，这是一个通用的解法。
