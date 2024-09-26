class `2024-09-26` {

    /* https://leetcode.com/problems/longest-consecutive-sequence/description/ */
    class Solution128 {
        fun longestConsecutive(nums: IntArray): Int {
            if(nums.isEmpty()) return 0

            val set = nums.toSet()

            var longest = 0

            for(num in nums) {
                if(!set.contains(num - 1)) {
                    var length = 1
                    var nextNum = num + 1
                    while (set.contains(nextNum)) {
                        length++
                        nextNum++
                    }
                    longest = maxOf(longest, length)
                }
            }

            return longest
        }
    }


    /* https://leetcode.com/problems/squares-of-a-sorted-array/description/ */
    class Solution977 {
        fun sortedSquares(nums: IntArray): IntArray {
            return nums.map { it * it }.sorted().toIntArray()
        }
    }

    /* https://leetcode.com/problems/reverse-string/description/ */
    class Solution344 {
        fun reverseString(s: CharArray) {
            for(i in 0..(s.lastIndex / 2)) {
                val pivot = s[i]
                s[i] = s[s.lastIndex - i]
                s[s.lastIndex - i] = pivot
            }
        }
    }

    /* https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/ */
    class Solution167 {
        fun twoSum(numbers: IntArray, target: Int): IntArray {
            for(i in numbers.indices) {
                for(j in (i + 1)..numbers.lastIndex) {
                    val added = numbers[i] + numbers[j]
                    if(added == target) return intArrayOf(i+1, j+1)
                }
            }

            //Impossible
            return intArrayOf()
        }
    }
}