import math
from functools import lru_cache

class Solution:
    def subsequencePairCount(self, nums: list[int]) -> int:
        MOD = 1_000_000_007
        n = len(nums)
        
        @lru_cache(None)
        def dp(i, g1, g2):
            # Base case: reached end of array
            if i == n:
                return 1 if (g1 > 0 and g2 > 0 and g1 == g2) else 0
            
            # Choice 1: Skip nums[i]
            res = dp(i + 1, g1, g2)
            
            # Choice 2: Add to seq1
            new_g1 = nums[i] if g1 == 0 else math.gcd(g1, nums[i])
            res = (res + dp(i + 1, new_g1, g2)) % MOD
            
            # Choice 3: Add to seq2
            new_g2 = nums[i] if g2 == 0 else math.gcd(g2, nums[i])
            res = (res + dp(i + 1, g1, new_g2)) % MOD
            
            return res
            
        return dp(0, 0, 0)   