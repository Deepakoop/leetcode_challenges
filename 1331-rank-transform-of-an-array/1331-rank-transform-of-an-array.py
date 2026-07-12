class Solution:
    def arrayRankTransform(self, arr: list[int]) -> list[int]:
        # 1. Identify unique elements and sort them in ascending order
        sorted_unique = sorted(set(arr))
        
        # 2. Use a dictionary to map each number to its rank
        # The rank is the index + 1 (since rank is 1-based)
        rank_map = {val: i + 1 for i, val in enumerate(sorted_unique)}
        
        # 3. Build the result by replacing each element with its rank
        return [rank_map[x] for x in arr]