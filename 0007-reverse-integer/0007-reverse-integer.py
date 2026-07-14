class Solution:
    def reverse(self, x: int) -> int:
        # Define 32-bit signed integer bounds
        MIN_INT = -2**31
        MAX_INT = 2**31 - 1
        
        # Determine the sign
        sign = -1 if x < 0 else 1
        x = abs(x)
        
        # Reverse the integer
        reversed_x = 0
        while x != 0:
            digit = x % 10
            x //= 10
            reversed_x = reversed_x * 10 + digit
        
        # Apply the sign
        result = sign * reversed_x
        
        # Check for overflow
        if result < MIN_INT or result > MAX_INT:
            return 0
            
        return result