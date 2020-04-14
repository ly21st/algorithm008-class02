/*
 * @lc app=leetcode.cn id=66 lang=golang
 *
 * [66] 加一
 */

package problems

// @lc code=start
func plusOne(digits []int) []int {
	var length = len(digits)
	if length < 1 {
		return digits;
	}
	var i = length - 1
	for i >= 0 {
		if digits[i] < 9 {
			digits[i]++
			return digits
		}
		digits[i] = 0
		i--
	}
	var result []int
	result = append(result, 1)
	result = append(result, digits...)
	return result
}
// @lc code=end

