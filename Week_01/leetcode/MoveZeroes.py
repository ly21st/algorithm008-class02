#
# @lc app=leetcode.cn id=283 lang=python
#
# [283] 移动零
#

# @lc code=start
class Solution(object):
    def moveZeroes(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        i = 0
        for j in range(len(nums)):
            if nums[j] != 0:
                if i != j:
                    nums[i] = nums[j];
                    nums[j] = 0
                i += 1


# @lc code=end


# class Solution(object):
#     def moveZeroes(self, nums):
#         """
#         :type nums: List[int]
#         :rtype: None Do not return anything, modify nums in-place instead.
#         """
#         i = 0
#         j = 0
#         nums_len = len(nums)
#         for j in range(nums_len):
#             if nums[j] != 0:
#                 nums[i] = nums[j];
#                 i += 1

#         while i < nums_len:
#             nums[i] = 0
#             i += 1




# class Solution(object):
#     def moveZeroes(self, nums):
#         """
#         :type nums: List[int]
#         :rtype: None Do not return anything, modify nums in-place instead.
#         """
#         i = 0
#         j = 0
#         nums_len = len(nums)
#         while j < nums_len:
#             if nums[j] != 0:
#                 nums[i] = nums[j];
#                 i += 1
#             j += 1


#         while i < nums_len:
#             nums[i] = 0
#             i += 1



## 快速排序的思想
# class Solution(object):
#     	def moveZeroes(self, nums):
# 		"""
# 		:type nums: List[int]
# 		:rtype: None Do not return anything, modify nums in-place instead.
# 		"""
# 		if not nums:
# 			return 0
# 		# 两个指针i和j
# 		j = 0
# 		for i in xrange(len(nums)):
# 			# 当前元素!=0，就把其交换到左边，等于0的交换到右边
# 			if nums[i]:
# 				nums[j],nums[i] = nums[i],nums[j]
# 				j += 1







# 理解：将数组里面的0移动到末尾，有几个0就移几个，其他的保持不变
#  解题思路

# 法一：统计0元素
# 法二：双指针滑动，交换非零元素和零元素的位置
# 法三：非零元素替换零元素法

#  法1
# class Solution:
#     def moveZeroes(self, nums: List[int]) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         # 循环记录0元素的个数，并且遇到非0元素时候，将非0元素替换到0元素的位置
#         # count 记录0元素的个数， i - count实际上是记录了零元素的位置。
#         count = 0
#         for i in range(len(nums)):
#             if nums[i] == 0:
#                 count += 1
#             elif count > 0:
#                 nums[i - count], nums[i] = nums[i], 0
#         return nums
        



#  复杂度分析

# 时间复杂度：O(n),假设有n个元素需要遍历n次
# 空间复杂度：O(1)，只是对原数组进行替换操作

#  法2
# class Solution:
#     def moveZeroes(self, nums: List[int]) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         # 循环遍历数组，当遇到非零元素则开始交换慢指针所指的0元素
#         # i 为慢指针 指向最新一个0元素的位置
#         i = 0
#         for j in range(len(nums)):
#             if nums[j] != 0:
#                 nums[i], nums[j] = nums[j], nums[i]
#                 i += 1
#         return nums
        

#  复杂度分析

# 时间复杂度：O(n),假设有n个元素需要遍历n次
# 空间复杂度：O(1)，只是对原数组进行替换操作

#  法3
# class Solution:
#     def moveZeroes(self, nums: List[int]) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         # 循环遍历数组，当遇到非零元素的时候替换掉前面0元素
#         # j 记录最新非零元素的位置
#         j = 0
#         for i in range(len(nums)):
#             if nums[i] != 0:
#                 nums[j] = nums[i]
#                 if i != j:
#                     nums[i] = 0
#                 j += 1
#         return nums
        



#  复杂度分析

# 时间复杂度：O(n),假设有n个元素需要遍历n次
# 空间复杂度：O(1)，只是对原数组进行替换操作

