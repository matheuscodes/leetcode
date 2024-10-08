class `2024-10-08` {

    /* https://leetcode.com/problems/maximum-average-subarray-i/description/ */
    class Solution643 {
        fun findMaxAverage(nums: IntArray, k: Int): Double {
            var maxSum = nums.sliceArray(0 until k).sum()
            var sum = maxSum
            for(i in 1 .. nums.size - k) {
                sum = sum + nums[i+k-1] - nums[i-1]
                if(maxSum < sum) maxSum = sum
            }
            return maxSum / k.toDouble()
        }
    }


    /* https://leetcode.com/problems/max-consecutive-ones-iii/description/ */
    class Solution1004 {
        fun longestOnes(nums: IntArray, k: Int): Int {
            var zeroCount = 0
            var left = 0
            var maxLength = Int.MIN_VALUE

            for(right in nums.indices){
                if(nums[right] == 0) zeroCount++

                while(zeroCount > k){
                    if(nums[left] == 0) zeroCount--
                    left++
                }
                maxLength = maxOf(maxLength, right - left + 1)
            }
            return maxLength
        }
    }

}