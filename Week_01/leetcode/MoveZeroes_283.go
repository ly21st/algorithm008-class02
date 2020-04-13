//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针
package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
func moveZeroes(nums []int)  {
	i := 0
	j := 0
	numsLen := len(nums)
	for j < numsLen {
		if nums[j] != 0 {
			if i != j {
				nums[i] = nums[j]
				nums[j] = 0
			}
			i += 1
		}
		j += 1
	}
}
//leetcode submit region end(Prohibit modification and deletion)


//func moveZeroes(nums []int)  {
//	i := 0
//	j := 0
//	numsLen := len(nums)
//	for j < numsLen {
//		if nums[j] != 0 {
//			nums[i], nums[j] = nums[j], nums[i]
//			i += 1
//		}
//		j += 1
//	}
//}





func main() {
	var nums = []int{0,1,0,3,12}
	moveZeroes(nums)
	fmt.Print(nums)
}