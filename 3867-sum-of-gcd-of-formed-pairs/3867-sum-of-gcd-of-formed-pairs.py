import math

class Solution:
    def gcdSum(self, nums: list[int]) -> int:
        n = len(nums)
        prefix_gcd = [0] * n
        mx = 0
        
        # Step 1: Build the prefixGcd array
        for i, x in enumerate(nums):
            mx = max(mx, x)
            prefix_gcd[i] = math.gcd(x, mx)
            
        # Step 2: Sort the array
        prefix_gcd.sort()
        
        # Step 3: Compute sum of GCDs of pairs
        total_sum = 0
        for i in range(n // 2):
            # Pair smallest (i) with largest (n - 1 - i)
            total_sum += math.gcd(prefix_gcd[i], prefix_gcd[n - 1 - i])
            
        return total_sum