#
# @lc app=leetcode.cn id=1221 lang=python3
#
# [1221] 分割平衡字符串
#

# @lc code=start
class Solution:
    def balancedStringSplit(self, s: str) -> int:
        s_len = len(s)
        balance_cnt = 0
        l_cnt, r_cnt = 0, 0
        for i in range(s_len):
            if s[i] == 'L':
                l_cnt += 1
            else:
                r_cnt += 1
            if l_cnt == r_cnt:
                balance_cnt += 1
                l_cnt = 0
                r_cnt = 0
        return balance_cnt

# @lc code=end

