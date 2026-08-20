class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode beforeStart = new ListNode(0);
        ListNode afterStart = new ListNode(0);
        
        ListNode before = beforeStart;
        ListNode after = afterStart;
        
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                before.next = current;
                before = before.next;
            } else {
                after.next = current;
                after = after.next;
            }
            current = current.next;
        }
        
        after.next = null;
        
        before.next = afterStart.next;
        
        return beforeStart.next;
    }
}