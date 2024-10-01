

class `2024-10-01` {

    /** https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */
    class Solution83 {
        class ListNode(var `val`: Int) {
            var next: ListNode? = null
        }

        fun deleteDuplicates(head: ListNode?): ListNode? {
            var cur = head
            while (cur?.next != null) {
                if (cur.`val` === cur.next!!.`val`) {
                    cur.next = cur.next!!.next
                } else {
                    cur = cur.next
                }
            }

            return head
        }
    }
}