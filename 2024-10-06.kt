import java.util.Stack

class `2024-10-06` {

    /* https://leetcode.com/problems/evaluate-reverse-polish-notation/description/ */
    class Solution150 {

        enum class Operation {
            ADD,
            SUBTRACT,
            MULTIPLY,
            DIVIDE,
            NUMBER
        }
        fun evalRPN(tokens: Array<String>): Int {
            val numbers = Stack<Int>()
            tokens.forEach { when(it.operation()) {
                Operation.NUMBER -> numbers.push(it.toInt())
                Operation.ADD -> {
                    val a = numbers.pop()
                    val b = numbers.pop()
                    numbers.push(a + b)
                }
                Operation.SUBTRACT -> {
                    val a = numbers.pop()
                    val b = numbers.pop()
                    numbers.push(b - a)
                }
                Operation.DIVIDE -> {
                    val a = numbers.pop()
                    val b = numbers.pop()
                    numbers.push(b / a)
                }
                Operation.MULTIPLY -> {
                    val a = numbers.pop()
                    val b = numbers.pop()
                    numbers.push(a * b)
                }
            } }
            return numbers.pop()
        }

        fun String.operation(): Operation {
            return when(this) {
                "+" -> Operation.ADD
                "-" -> Operation.SUBTRACT
                "*" -> Operation.MULTIPLY
                "/" -> Operation.DIVIDE
                else -> Operation.NUMBER
            }
        }
    }

    /* https://leetcode.com/problems/daily-temperatures/description/ */
    class Solution739 {
        fun dailyTemperatures(temperatures: IntArray): IntArray {
            val pending = Stack<Pair<Int, Int>>()
            val response = IntArray(temperatures.size)
            for(i in temperatures.indices) {
                if(pending.isNotEmpty()) {
                    pending.forEach { print(it) }
                    println()
                    var p: Pair<Int, Int>? = pending.pop()
                    while (p != null && temperatures[i] > p.second) {
                        response[p.first] = i - p.first
                        if(pending.isNotEmpty()) p = pending.pop()
                        else p = null
                    }
                    pending.push(p)
                }
                pending.push(i to temperatures[i])
            }

            return response
        }
    }


    /* https://leetcode.com/problems/min-stack/description/ */
    class MinStack() {
        private val top = Stack<Int>()
        private val min = Stack<Int>()

        fun push(value: Int) {
            top.push(value)
            if (min.isEmpty() || value <= min.peek()) {
                min.push(value)
            }
        }

        fun pop() {
            if (top.peek() == min.peek()) {
                min.pop()
            }
            top.pop()
        }

        fun top(): Int {
            return top.peek()
        }

        fun getMin(): Int {
            return min.peek()
        }
    }

    /* https://leetcode.com/problems/reverse-linked-list/description/ */
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    class Solution206 {

        fun reverseList(head: ListNode?): ListNode? {
            var prev: ListNode? = null
            var cur: ListNode? = head

            while (cur != null) {
                val temp = cur.next
                cur.next = prev
                prev = cur
                cur = temp
            }

            return prev
        }
    }


    /* https://leetcode.com/problems/merge-two-sorted-lists/description/ */
    class Solution21 {
        fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
            var point1 = list1
            var point2 = list2
            var result: ListNode?

            if(point1 != null && point2 != null) {
                if(point1.`val` > point2.`val`) {
                    result = point2
                    point2 = point2.next
                    result.next = null
                } else {
                    result = point1
                    point1 = point1.next
                    result.next = null
                }
            } else if(point1 == null && point2 != null) {
                result = point2
                point2 = point2.next
                result.next = null
            } else if(point1 != null) {
                result = point1
                point1 = point1.next
                result.next = null
            } else {
                return null
            }

            val start = result
            while (point1 != null || point2 != null) {
                if(point1 != null && point2 != null) {
                    if(point1.`val` > point2.`val`) {
                        result!!.next = point2
                        point2 = point2.next
                        result = result.next
                        result!!.next = null
                    } else {
                        result!!.next = point1
                        point1 = point1.next
                        result = result.next
                        result!!.next = null
                    }
                } else
                if(point1 == null) {
                    result!!.next = point2
                    point2 = point2!!.next
                    result = result.next
                    result!!.next = null
                } else {
                    result!!.next = point1
                    point1 = point1.next
                    result = result.next
                    result!!.next = null
                }
            }
            return start
        }
    }

    /* https://leetcode.com/problems/linked-list-cycle/description/ */
    class Solution141 {
        fun hasCycle(head: ListNode?): Boolean {
            val dummy = ListNode(0)
            dummy.next = head
            var slow: ListNode? = dummy
            var fast: ListNode? = dummy

            while (fast != null && fast.next != null) {
                slow = slow!!.next
                fast = fast.next!!.next
                if (slow === fast) {
                    return true
                }
            }

            return false
        }
    }

    /* https://leetcode.com/problems/middle-of-the-linked-list/description/ */
    class Solution876 {
        fun middleNode(head: ListNode?): ListNode? {
            var count = 0
            var p = head
            while(p != null) {
                p = p.next
                count++
            }

            p = head
            count /= 2
            while(count > 0) {
                p = p!!.next
                count--
            }

            return p
        }
    }

    /* https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/ */
    class Solution19 {
        fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
            if(head == null) return null
            val returned = goDeep(head, n)
            if(returned.first == n) {
                return returned.second.next
            }
            return head
        }

        fun goDeep(head: ListNode, n: Int): Pair<Int, ListNode> {
            head.next?.let {
                val returned = goDeep(it, n)
                if(returned.first == n) {
                    head.next = returned.second.next
                }
                return returned.first + 1 to head
            } ?: run {
                return 1 to head
            }
        }
    }

    /* https://leetcode.com/problems/copy-list-with-random-pointer/description/ */
    class Node(var `val`: Int) {
         var next: Node? = null
         var random: Node? = null
    }
    class Solution138 {
        fun copyRandomList(node: Node?): Node? {
            if(node == null) return null
            val mapVals = mutableMapOf<Node, Node>()
            var p = node
            while (p != null) {
                val clone = getOrCreate(mapVals, p.`val`, p)
                p.next?.let {
                    clone.next = getOrCreate(mapVals, it.`val`, it)
                }
                p.random?.let {
                    clone.random = getOrCreate(mapVals, it.`val`, it)
                }
                p = p.next
            }
            return mapVals.get(node)
        }

        fun getOrCreate(map: MutableMap<Node, Node>, value: Int, address: Node): Node {
            map.get(address)?.let {
                return it
            } ?: run {
                val newone = Node(value)
                map.put(address, newone)
                return newone
            }
        }
    }
}
