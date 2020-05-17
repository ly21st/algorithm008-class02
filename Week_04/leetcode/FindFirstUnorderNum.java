//package problems-0004;


public class FindFirstUnorderNum {
    public int findFirstNum(int []nums) {
        int len = nums.length;
        if (len <= 2) return len - 1;
        int i = 0;  
        int j = len - 1;
        int mid;
        System.out.println("111");
        while ( j - i > 2) {
            mid = i + (j - i) / 2;
            System.out.println("mid:" + mid + " =" + nums[mid]);
            if (nums[i] <= nums[mid]) {
                if (j - mid > 1) {
                    i = mid;
                } else {
                    return nums[j];
                }
            } else {
                if (mid - i > 1) {
                     j = mid;
                } else {
                    return nums[mid];
                }
            }
        }
        return nums[j];
    }

    public static void main(String[] args) {
        FindFirstUnorderNum s = new FindFirstUnorderNum();
        int []nums = { 4, 5, 6, 7, 0, 1, 2};
        System.out.println(s.findFirstNum(nums));
    }
}