# @before-stub-for-debug-begin
from python3problem189 import *
from typing import *
# @before-stub-for-debug-end

#
# @lc app=leetcode.cn id=189 lang=python3
#
# [189] 旋转数组
#

# @lc code=start
class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return 
        nums_len = len(nums)
        if nums_len < 2:
            return 
        k = k % nums_len
        if not k:
            return 

        n = self._gcd(nums_len, nums_len - k)
        while n > 0:
            n -=1
            self._rotate(nums, nums_len, n, nums_len - k)
            

    def _gcd(self, m: int, n: int):
        while n != 0:
            t = m % n;
            m = n
            n = t
        return m

    def _rotate(self, nums: List[int], last: int, initial: int, diff: int):
        tmp = nums[initial]
        ptr1 = initial
        ptr2 = ptr1 + diff
        while ptr2 != initial:
            nums[ptr1] = nums[ptr2]
            ptr1 = ptr2
            if ptr2 + diff < last:
                ptr2 = ptr2 + diff
            else:
                ptr2 = diff - (last - ptr2)
        nums[ptr1] = tmp



# @lc code=end






# class Solution:
#     def rotate(self, nums: List[int], k: int) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         if not nums:
#             return 
#         nums_len = len(nums)
#         if nums_len < 2:
#             return 
#         k = k % nums_len
#         if not k:
#             return 
#         self.reserve(nums, 0, nums_len - k)
#         self.reserve(nums, nums_len - k, nums_len)
#         self.reserve(nums, 0, nums_len)

#     def reserve(self, nums: List[int], first: int, end: int):
#         last = end - 1
#         while first < last:
#             nums[first], nums[last] = nums[last], nums[first]
#             first += 1
#             last -= 1





# class Solution:
#     def rotate(self, nums: List[int], k: int) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         if not nums:
#             return 
#         nums_len = len(nums)
#         if nums_len < 2:
#             return 
#         k = k % nums_len
#         if not k:
#             return 

#         i, j = 0, nums_len - k
#         middle = nums_len - k
#         while True:
#             while i < middle and j < nums_len:
#                 nums[i], nums[j] = nums[j], nums[i]
#                 i += 1
#                 j += 1
#             if i == middle:
#                 if j == nums_len:
#                     return
#                 middle = j
#             else:
#                 j = middle




# class Solution:
#     def rotate(self, nums: List[int], k: int) -> None:
#         """
#         Do not return anything, modify nums in-place instead.
#         """
#         if not nums:
#             return 
#         nums_len = len(nums)
#         if nums_len < 2:
#             return 
#         k = k % nums_len
#         if not k:
#             return 

#         self.rotate_aux(nums, 0, nums_len - k, nums_len)

#     def rotate_aux(self, nums: List[int], first: int, middle: int, last: int):
#         if last - first <= 1:
#             return
#         i, j = first, middle;
#         while i < middle and j < last:
#                 nums[i], nums[j] = nums[j], nums[i]
#                 i += 1
#                 j += 1
#         if i == middle:
#             if j == last:
#                 return
#             self.rotate_aux(nums, i, j, last)
#         else:
#             self.rotate_aux(nums, i, middle, last)

