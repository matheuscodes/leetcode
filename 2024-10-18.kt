class `2024-10-18` {

    /* https://leetcode.com/problems/combination-sum/description/ */
    class Solution39 {
        fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
            return backtrack(mutableListOf(), candidates, target).toList()
        }

        fun backtrack(combination: MutableList<Int>, source: IntArray, target: Int, currentSum: Int = 0, found: MutableSet<List<Int>> = mutableSetOf()): Set<List<Int>> {
            if(currentSum == target) {
                found.add(combination.sorted())
            }
            if(currentSum >= target) {
                return found
            }
            for(i in source.indices) {
                combination.add(source[i])
                backtrack(combination, source, target, currentSum + source[i], found)
                combination.removeAt(combination.lastIndex)
            }
            return found
        }
    }

    /* https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/ */
    class Solution17 {
        fun letterCombinations(digits: String): List<String> {
            val map = mutableMapOf<String, List<String>>()
            map.put("2", listOf("a", "b", "c"))
            map.put("3", listOf("d", "e", "f"))
            map.put("4", listOf("g", "h", "i"))
            map.put("5", listOf("j", "k", "l"))
            map.put("6", listOf("m", "n", "o"))
            map.put("7", listOf("p", "q", "r", "s"))
            map.put("8", listOf("t", "u", "v"))
            map.put("9", listOf("w", "x", "y", "z"))
            return iterate(digits.map { "$it" }.toMutableList(), map)
        }

        fun iterate(remainingDigits: MutableList<String>, map: Map<String, List<String>>, found: List<String> = listOf()): List<String> {
            if(remainingDigits.isEmpty()) return found
            val digit = remainingDigits.removeAt(0)
            map[digit]?.let { remainingLetters ->
                val newFound = mutableListOf<String>()
                if(found.isEmpty()) {
                    remainingLetters.forEach { newFound.add(it) }
                } else {
                    found.forEach { combination ->
                        remainingLetters.forEach { letter ->
                            newFound.add(combination.plus(letter))
                        }
                    }
                }
                return iterate(remainingDigits, map, newFound)
            }
            return found
        }
    }


    /* https://leetcode.com/problems/generate-parentheses/description/ */
    class Solution22 {
        fun generateParenthesis(n: Int): List<String> {
            return dfs(0,0,n,"")
        }

        fun dfs(open: Int, closed: Int, target: Int, current: String, list: MutableList<String> = mutableListOf()): List<String> {
            if(open == closed && open == target) {
                list.add(current)
            }
            if(open < target) {
                 dfs(open + 1, closed, target, "$current(", list)
            }
            if(closed < open){
                dfs(open, closed + 1, target, "$current)", list)
            }
            return list
        }
    }

    /* https://leetcode.com/problems/word-search/description/ */
    class Solution79 {

        fun exist(board: Array<CharArray>, word: String): Boolean {
            val boardSet = mutableSetOf<Char>()
            val wordSet = mutableSetOf<Char>()
            board.forEach { it.forEach { a -> boardSet.add(a) } }
            word.toCharArray().forEach {  a -> wordSet.add(a) }
            if(boardSet.containsAll(wordSet)) {
                for (i in board.indices) {
                    for (j in board[i].indices) {
                        if ("${board[i][j]}" == word) return true
                        if (find(board, listOf(i to j), word)) return true
                    }
                }
            }
            return false
        }

        fun find(board: Array<CharArray>, list: List<Pair<Int, Int>>, target: String): Boolean {
            val nexts = getNext(board, list[list.lastIndex], list)
            var found = false
            nexts.forEach {
                val next = list.plus(it)
                val word = readWord(board, next)
                if(target == word) {
                    found = true
                } else if(target.startsWith(word)) {
                    found = found || find(board, next, target)
                }
            }
            return found
        }

        fun readWord(board: Array<CharArray>, list: List<Pair<Int, Int>>): String {
            var word = ""
            list.map { board[it.first][it.second] }.forEach { word += it }
            return word
        }

        fun getNext(board: Array<CharArray>, point: Pair<Int, Int>, visited: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
            val list = mutableListOf<Pair<Int, Int>>()
            if(point.first < board.lastIndex) {
                list.add(point.first + 1 to point.second)
            }
            if(point.second < board[0].lastIndex) {
                list.add(point.first to point.second + 1)
            }
            if(point.first > 0) {
                list.add(point.first - 1 to point.second)
            }
            if(point.second > 0) {
                list.add(point.first to point.second - 1)
            }
            return list.filter { !visited.contains(it) }
        }
    }
}