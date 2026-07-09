# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        # Dummy node simplifies edge cases handling at the head of the list
        dummy = ListNode(0, head)
        groupPrev = dummy
        
        while True:
            # 1. Find the kth node of the current group
            kth = self.getKth(groupPrev, k)
            if not kth:
                break # If there are fewer than k nodes left, we are done
                
            # Store the pointer to the node right after this k-group
            groupNext = kth.next
            
            # 2. Reverse the current k-group
            prev, curr = kth.next, groupPrev.next
            while curr != groupNext:
                tmp = curr.next
                curr.next = prev
                prev = curr
                curr = tmp
                
            # 3. Re-link the reversed group back into the main list
            tmp = groupPrev.next # This is now the tail of the reversed group
            groupPrev.next = kth # kth is now the new head of the reversed group
            groupPrev = tmp      # Move groupPrev to the tail for the next iteration
            
        return dummy.next
        
    def getKth(self, curr, k):
        while curr and k > 0:
            curr = curr.next
            k -= 1
        return curr