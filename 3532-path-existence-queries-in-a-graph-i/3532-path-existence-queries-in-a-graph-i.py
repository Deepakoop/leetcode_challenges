from typing import List

class Solution:
    def pathExistenceQueries(self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]) -> List[bool]:
        # g[i] will store the component ID for node i
        g = [0] * n
        cnt = 0
        
        # Segment the sorted array into components
        for i in range(1, n):
            # If the gap between adjacent elements is too large, it's a new component
            if nums[i] - nums[i - 1] > maxDiff:
                cnt += 1
            g[i] = cnt
            
        # For each query, they are connected if they belong to the same component
        return [g[u] == g[v] for u, v in queries]