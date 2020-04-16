#
# @lc app=leetcode.cn id=1281 lang=python3
#
# [1281] 整数的各位积和之差
#

# @lc code=start
class Solution:
    def subtractProductAndSum(self, n: int) -> int:
        product = 1
        sum = 0
        while n != 0:
            t = int(n % 10)
            product *= t
            sum += t
            n = int(n / 10)
        return product - sum
# @lc code=end

