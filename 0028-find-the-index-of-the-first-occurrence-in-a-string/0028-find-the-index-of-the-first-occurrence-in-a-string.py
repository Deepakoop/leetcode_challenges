class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        # Edge case: if needle is empty, return 0
        if not needle:
            return 0
            
        n = len(haystack)
        m = len(needle)
        
        # Iterate through the haystack
        # We only need to go up to (n - m + 1)
        for i in range(n - m + 1):
            # Check if the substring starting at i matches the needle
            if haystack[i : i + m] == needle:
                return i
                
        return -1