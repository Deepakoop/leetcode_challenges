from bisect import bisect_left
from collections import Counter

class Solution:
    def gcdValues(self, nums: list[int], queries: list[int]) -> list[int]:
        mx = max(nums)
        cnt = Counter(nums)
        
        # count_divisible[i] stores how many numbers in nums are divisible by i
        count_divisible = [0] * (mx + 1)
        for i in range(1, mx + 1):
            for j in range(i, mx + 1, i):
                count_divisible[i] += cnt[j]
        
        # count_gcd[i] will store how many pairs have gcd exactly i
        count_gcd = [0] * (mx + 1)
        for i in range(mx, 0, -1):
            v = count_divisible[i]
            # Total pairs with common divisor i
            count_gcd[i] = v * (v - 1) // 2
            # Subtract pairs that have a larger multiple of i as their gcd
            for j in range(2 * i, mx + 1, i):
                count_gcd[i] -= count_gcd[j]
        
        # Build prefix sums
        prefix_sum = [0] * (mx + 1)
        for i in range(1, mx + 1):
            prefix_sum[i] = prefix_sum[i - 1] + count_gcd[i]
            
        # Answer queries
        ans = []
        for q in queries:
            # Find the first index where prefix_sum[i] > q
            ans.append(bisect_left(prefix_sum, q + 1))
            
        return ans    