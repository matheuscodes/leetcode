import kotlin.math.min

class `2024-09-24` {

    /* https://leetcode.com/problems/jewels-and-stones/description/ */
    class Solution771 {
        fun numJewelsInStones(jewels: String, stones: String): Int {
            var count = 0
            for (jewel in jewels) {
                count += stones.count { it == jewel }
            }
            return count
        }
    }

    /* https://leetcode.com/problems/contains-duplicate/description/ */
    class Solution217 {
        fun containsDuplicate(nums: IntArray): Boolean {
            val asSet = nums.toSet()
            if(asSet.size == nums.size) return false
            return true
        }
    }


    /* https://leetcode.com/problems/ransom-note/description/ */
    class Solution383 {
        fun canConstruct(ransomNote: String, magazine: String): Boolean {
            val cuttings = magazine.groupByTo(mutableMapOf()) { it }
            for(letter in ransomNote) {
                val cut = cuttings.getOrDefault(letter, mutableListOf())
                if(cut.size <= 0) {
                    return false
                }
                cut.remove(letter)
            }
            return true
        }
    }

    /* https://leetcode.com/problems/valid-anagram/description/ */
    class Solution242 {
        fun isAnagram(s: String, t: String): Boolean {
            if(s.length != t.length) return false
            val letterss = s.groupByTo(mutableMapOf()) { it }
            val letterst = t.groupByTo(mutableMapOf()) { it }
            return  letterss == letterst
        }
    }

    /* https://leetcode.com/problems/maximum-number-of-balloons/description/ */
    class Solution1189 {
        fun maxNumberOfBalloons(text: String): Int {
            val letters = text.groupByTo(mutableMapOf()) { it }
            val minSingles = minOf(
                    letters.getOrDefault('b', emptyList()).size,
                    letters.getOrDefault('a', emptyList()).size,
                    letters.getOrDefault('n', emptyList()).size,
            )

            val minDoubles = minOf(
                    letters.getOrDefault('o', emptyList()).size,
                    letters.getOrDefault('l', emptyList()).size,
            )

            return min(minSingles, minDoubles / 2)
        }
    }


    /* https://leetcode.com/problems/two-sum/description/ */
    class Solution1 {
        fun twoSum(nums: IntArray, target: Int): IntArray {
            val map: HashMap<Int, Int> = hashMapOf()

            for (i in nums.indices) {
                val index = map.get(target - nums[i])
                index?.let { return intArrayOf(index, i) }
                map.put(nums[i], i)
            }
            return intArrayOf()
        }
    }
}