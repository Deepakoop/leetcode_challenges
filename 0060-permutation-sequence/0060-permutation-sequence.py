import math

class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        # Create a list of numbers to use
        nums = [str(i) for i in range(1, n + 1)]
        # Adjust k to be 0-indexed
        k -= 1
        
        # Precompute (n-1)!
        fact = math.factorial(n - 1)
        
        result = []
        for i in range(n - 1, 0, -1):
            # Determine the index of the current digit
            index = k // fact
            result.append(nums.pop(index))
            
            # Update k and factorial for the next position
            k %= fact
            fact //= i
            
        # Append the last remaining number
        result.append(nums[0])
        
        return "".join(result)