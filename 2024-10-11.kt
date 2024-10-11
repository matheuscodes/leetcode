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

    /* https://leetcode.com/problems/permutation-in-string/description/ */
    class Solution567 {
        fun checkInclusion(s1: String, s2: String): Boolean {
            if (s1.length > s2.length) return false
            val s1MapArray = IntArray(26) { 0 }
            val s2MapArray = IntArray(26) { 0 }

            for (i in s1.indices) {
                s1MapArray[s1[i] - 'a']++
                s2MapArray[s2[i] - 'a']++
            }

            var l = 0
            var r = s1.length
            while(r < s2.length) {
                if (s1MapArray.contentEquals(s2MapArray)) return true
                s2MapArray[s2[l] - 'a']--
                s2MapArray[s2[r] - 'a']++
                l += 1
                r += 1
            }
            return s1MapArray.contentEquals(s2MapArray)
        }
    }
}