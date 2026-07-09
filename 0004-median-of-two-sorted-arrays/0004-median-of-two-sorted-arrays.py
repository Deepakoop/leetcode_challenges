class Solution:
    def findMedianSortedArrays(self, nums1: list[int], nums2: list[int]) -> float:
        # Ensure nums1 is the smaller array to minimize the binary search range
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
            
        A, B = nums1, nums2
        total = len(A) + len(B)
        half = total // 2
        
        # Binary search boundaries on the smaller array A
        l, r = 0, len(A) - 1
        
        while True:
            # Index for partitioning array A
            i = (l + r) // 2  
            # Index for partitioning array B
            j = half - i - 2  
            
            # Handle out-of-bounds cases using infinity
            Aleft = A[i] if i >= 0 else float('-inf')
            Aright = A[i + 1] if (i + 1) < len(A) else float('inf')
            Bleft = B[j] if j >= 0 else float('-inf')
            Bright = B[j + 1] if (j + 1) < len(B) else float('inf')
            
            # Check if we have found the correct partition
            if Aleft <= Bright and Bleft <= Aright:
                # If total number of elements is odd
                if total % 2:
                    return min(Aright, Bright)
                # If total number of elements is even
                return (max(Aleft, Bleft) + min(Aright, Bright)) / 2
                
            elif Aleft > Bright:
                # We took too many elements from A's left side, move left
                r = i - 1
            else:
                # We need more elements from A's left side, move right
                l = i + 1      