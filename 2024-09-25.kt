class `2024-09-25` {

    /* https://leetcode.com/problems/valid-sudoku/description/ */
    class Solution36 {
        fun isValidSudoku(board: Array<CharArray>): Boolean {
            val hashesi = mutableMapOf<Char, MutableSet<Int>>()
            val hashesj = mutableMapOf<Char, MutableSet<Int>>()
            val hashesq = mutableMapOf<Char, MutableSet<Int>>()
            for(i in board.indices) {
                for (j in board[i].indices) {
                    if(board[i][j] != '.') {
                        hashesi[board[i][j]]?.let {
                            if(it.contains(i)) return false
                            it.add(i)
                        } ?: run {
                            hashesi[board[i][j]] = mutableSetOf(i)
                        }
                        hashesj[board[i][j]]?.let {
                            if(it.contains(j)) return false
                            it.add(j)
                        } ?: run {
                            hashesj[board[i][j]] = mutableSetOf(j)
                        }
                        val q = ijToq(i, j)
                        hashesq[board[i][j]]?.let {
                            if(it.contains(q)) return false
                            it.add(q)
                        } ?: run {
                            hashesq[board[i][j]] = mutableSetOf(q)
                        }
                    }
                }
            }
            return true
        }

        fun ijToq(i: Int, j: Int): Int {
            return (i / 3) * 10 + (j / 3)
        }
    }


    /* https://leetcode.com/problems/group-anagrams/description/ */
    class Solution49 {
        fun groupAnagrams(strs: Array<String>): List<List<String>> {
            val grouped = strs.groupBy { it.toCharArray().sorted() }

            return grouped.map { it.value }
        }
    }

    /* https://leetcode.com/problems/majority-element/description/ */
    class Solution169 {
        fun majorityElement(nums: IntArray): Int {
            val counts = mutableMapOf<Int, Int>()
            val majority = (nums.size / 2)

            for(num in nums) {
                val value = (counts[num] ?: 0) + 1
                if(value > majority ) return num
                counts[num] = value
            }

            return -1
        }
    }

}