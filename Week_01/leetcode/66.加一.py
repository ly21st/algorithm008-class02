#
# @lc app=leetcode.cn id=66 lang=python3
#
# [66] 加一
#

# @lc code=start
class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        if not digits:
            return digits
        digits_len = len(digits)
        for j in range(digits_len - 1, -1, -1):
            if digits[j] < 9:
                digits[j] += 1
                return digits
            else:
                digits[j] = 0 
        return [1] + digits


# @lc code=end


# class Solution:
#     def plusOne(self, digits: List[int]) -> List[int]:
#         if not digits:
#             return digits
#         digits_len = len(digits)
#         for j in range(digits_len - 1, -1, -1):
#             if digits[j] < 9:
#                 digits[j] += 1
#                 return digits
#             else:
#                 digits[j] = 0 
#         return [1] + digits