import java.util.Locale

class `2024-09-27` {

    /* https://leetcode.com/problems/valid-palindrome/description/ */
    class Solution125 {
        fun isPalindrome(s: String): Boolean {
            val simplified = s
                    .lowercase(Locale.getDefault())
                    .toCharArray()
                    .filter { it in 'a'..'z' || it in '0'..'9' }

            if(simplified.isEmpty()) return true

            for(i in 0..(simplified.lastIndex / 2)) {
                if(simplified[i] != simplified[simplified.lastIndex - i]) return false
            }

            return true
        }
    }

    /* https://leetcode.com/problems/3sum/description/ */
    class Solution15 {
        fun threeSum(nums: IntArray): List<List<Int>> {
            nums.sort()
            val answer = mutableListOf<List<Int>>()

            for (i in nums.indices) {
                if (nums[i] > 0) {
                    break
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue
                }
                var lo = i + 1
                var hi = nums.lastIndex
                while (lo < hi) {
                    val sum = nums[i] + nums[lo] + nums[hi]
                    if (sum == 0) {
                        answer.add(listOf(nums[i], nums[lo], nums[hi]))
                        lo++
                        hi--
                        while (lo < hi && nums[lo] == nums[lo - 1]) lo++
                        while (lo < hi && nums[hi] == nums[hi + 1]) hi--
                    } else if (sum < 0) {
                        lo++
                    } else {
                        hi--
                    }
                }
            }

            return answer
        }
    }
}