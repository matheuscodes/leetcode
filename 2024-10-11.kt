class `2024-10-11` {
    /* https://leetcode.com/problems/minimum-size-subarray-sum/description/ */
    class Solution209 {
        fun minSubArrayLen(target: Int, nums: IntArray): Int {
            var l = 0
            var r = 0
            var currentSum = nums[r]
            var min = Int.MAX_VALUE
            while(l <= r) {
                if(currentSum >= target) {
                    min = minOf(min, r - l + 1)
                }
                if(currentSum < target && r < nums.lastIndex) {
                    r += 1
                    currentSum += nums[r]
                } else {
                    currentSum -= nums[l]
                    l += 1
                }
            }
            return if(min > nums.size) 0 else min
        }
    }
}