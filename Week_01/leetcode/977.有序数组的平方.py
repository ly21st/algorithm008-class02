#
# @lc app=leetcode.cn id=977 lang=python3
#
# [977] 有序数组的平方
#


# @lc code=start
class Solution:
    def sortedSquares(self, A: List[int]) -> List[int]:
        A_len = len(A)
        i = 0
        j = A_len - 1
        t = j
        result = [0] * A_len
        while t >= 0:
            product1 = A[i] * A[i]
            product2 = A[j] * A[j]
            if product1 <= product2:
                result[t] = product2
                j -= 1
            else:
                result[t] = product1
                i += 1
            t -= 1
        return result
            
# @lc code=end



# class Solution:
#     def sortedSquares(self, A: List[int]) -> List[int]:
#         A_len = len(A)
#         if not A:
#             return A
#         for i in range(0, A_len):
#             product = A[i] * A[i]
#             j = i -1
#             while j >= 0 and product < A[j]:
#                 A[j+1] = A[j]
#                 j -= 1
#             A[j+1] = product
#         return A



# class Solution:
#     def sortedSquares(self, A: List[int]) -> List[int]:
#         A_len = len(A)
#         i = 0
#         while i < A_len and A[i] < 0:
#             i += 1
#         j = i - 1
#         result = [0] * A_len
#         t = 0
#         while j >= 0 and i < A_len:
#             product1 = A[j] * A[j]
#             product2 = A[i] * A[i]
#             if product1 <= product2:
#                 result[t] = product1
#                 j -= 1
#                 t += 1
#             else:
#                 result[t] = product2
#                 i += 1
#                 t += 1
#         while j >= 0:
#             result[t] = A[j] * A[j]
#             j -= 1
#             t += 1
#         while i < A_len:
#             result[t] = A[i] * A[i]
#             i += 1
#             t += 1
#         return result



# class Solution:
#     def sortedSquares(self, A: List[int]) -> List[int]:
#         A_len = len(A)
#         if not A:
#             return A
#         for i in range(0, A_len):
#             A[i] = A[i] * A[i]
#         B = sorted(A)
#         return B