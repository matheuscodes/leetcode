import kotlin.math.abs

class `2024-09-22` {

    /* https://leetcode.com/problems/find-closest-number-to-zero/description/ */
    class Solution2239 {
        fun findClosestNumber(nums: IntArray): Int {
            var found = nums[0]
            var closest = abs(nums[0])
            for (num in nums) {
                val distance = abs(num)
                if (distance < closest) {
                    found = num
                    closest = abs(num)
                } else if (distance == closest && num > found) {
                    found = num
                }
            }
            return found
        }
    }

    /* https://leetcode.com/problems/merge-strings-alternately/description/ */
    class Solution1768 {
        fun mergeAlternately(word1: String, word2: String): String {
            val large = if(word1.length > word2.length) word1 else word2
            val merged = StringBuilder()
            for(i in 0..large.length) {
                if(i < word1.length) {
                    merged.append(word1[i])
                }
                if(i < word2.length) {
                    merged.append(word2[i])
                }
            }
                return merged.toString()
        }
    }


    /* https://leetcode.com/problems/roman-to-integer/description/ */
    class Solution13 {
        fun romanToInt(s: String): Int {
            var skip = false
            val mapped: List<Int> = s.mapIndexed { index, c ->
                if(!skip) {
                    val inside = index + 1 < s.length
                    when (c) {
                        'I' -> if(inside && s[index + 1] == 'X') {
                            skip = true
                            9
                        } else  if(inside && s[index + 1] == 'V') {
                            skip = true
                            4
                        } else 1
                        'X' -> if(inside && s[index + 1] == 'C') {
                            skip = true
                            90
                        } else  if(inside && s[index + 1] == 'L') {
                            skip = true
                            40
                        } else 10
                        'C' -> if(inside && s[index + 1] == 'M') {
                            skip = true
                            900
                        } else  if(inside && s[index + 1] == 'D') {
                            skip = true
                            400
                        } else 100
                        'V' -> 5
                        'L' -> 50
                        'D' -> 500
                        'M' -> 1000
                        else -> 0
                    }
                } else {
                    skip = false
                    0
                }
            }
            return mapped.sum()
        }
    }


    /* https://leetcode.com/problems/is-subsequence/description/ */
    class Solution392 {
        fun isSubsequence(s: String, t: String): Boolean {
            if(s == "") return true
            var lastIndex = 0
            for(c in t) {
                if(c == s[lastIndex]) lastIndex++
                if(lastIndex >= s.length) break
            }
            return lastIndex == s.length
        }
    }

    /* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/ */
    class Solution121 {
        fun maxProfit(prices: IntArray): Int {
            var minPrice = prices[0]
            var maxProfit = 0
            for (p in prices) {
                if (p < minPrice) {
                    minPrice = p
                } else {
                    maxProfit = maxOf(maxProfit, p - minPrice)
                }
            }
            return maxProfit
        }
    }


    /* https://leetcode.com/problems/longest-common-prefix/description/ */
    class Solution14 {
        fun longestCommonPrefix(strs: Array<String>): String {
            val sample = strs[0]
            var largest = ""
            for(i in 0..sample.length) {
                val prefix = sample.substring(0, i)
                for(s in strs) {
                    if(!s.startsWith(prefix)) {
                        return largest
                    }
                }
                largest = prefix
            }
            return largest
        }
    }

    /* https://leetcode.com/problems/summary-ranges/description/ */
    class Solution228 {
        fun summaryRanges(nums: IntArray): List<String> {
            if(nums.isEmpty()) return emptyList()
            var current = nums[0]
            var next = nums[0]
            val strings = mutableListOf<String>()
            for(num in nums) {
                if (num > next + 1) {
                    strings.addFormatted(current, next)
                    current = num
                }
                next = num
            }
            strings.addFormatted(current, next)
            return strings
        }

        fun MutableList<String>.addFormatted(current: Int, next: Int) {
            if(current == next) this.add(current.toString())
            else this.add("$current->$next")
        }
    }

    /* https://leetcode.com/problems/product-of-array-except-self/description/ */
    class Solution238 {
        fun productExceptSelf(nums: IntArray): IntArray {
            val result = IntArray(nums.size)

            var leftProduct = 1
            for (i in nums.indices) {
                result[i] = leftProduct
                leftProduct *= nums[i]
            }

            var rightProduct = 1
            for (i in nums.lastIndex downTo 0) {
                result[i] *= rightProduct
                rightProduct *= nums[i]
            }

            return result
        }
    }


}