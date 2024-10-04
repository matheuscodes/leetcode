class `2024-10-04` {

    /* https://leetcode.com/problems/search-insert-position/description/ */
    class Solution35 {
        fun searchInsert(nums: IntArray, target: Int): Int {
            var l = 0
            var r = nums.lastIndex

            while (l <= r) {
                val m = (l + r) / 2
                if (nums[m] < target) {
                    l = m + 1
                } else if (nums[m] > target) {
                    r = m - 1
                } else {
                    return m
                }
            }

            return l
        }
    }
}