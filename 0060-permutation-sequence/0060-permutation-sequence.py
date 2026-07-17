import math

class Solution:
    def getPermutation(self, n: int, k: int) -> str:
        # Create a list of available numbers: [1, 2, ..., n]
        numbers = [str(i) for i in range(1, n + 1)]
        
        # Adjust k to be 0-indexed for easier calculation
        k -= 1
        
        # Calculate (n-1)!
        factorial = math.factorial(n - 1)
        
        result = []
        
        # Iterate through each position
        for i in range(n - 1, 0, -1):
            # Identify the index of the digit to use
            index = k // factorial
            result.append(numbers.pop(index))
            
            # Update k and the factorial for the next step
            k %= factorial
            factorial //= i
            
        # Add the final remaining digit
        result.append(numbers[0])
        
        return "".join(result)