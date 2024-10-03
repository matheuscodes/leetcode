class `2024-10-03` {

    /* https://leetcode.com/problems/binary-search/description/ */
    class Solution704 {
        fun search(nums: IntArray, target: Int): Int {
            var left = 0
            var right = nums.lastIndex

            while (left <= right) {
                val middle = left + (right - left) / 2
                if (nums[middle] == target) {
                    return middle
                } else if (nums[middle] > target) {
                    right = middle - 1
                } else {
                    left = middle + 1
                }
            }

            return -1
        }
    }
}