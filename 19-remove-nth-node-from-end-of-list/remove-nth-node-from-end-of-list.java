class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node before head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy, second = dummy;

        // Move first pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Remove the nth node
        second.next = second.next.next;

        return dummy.next;
    }
}
