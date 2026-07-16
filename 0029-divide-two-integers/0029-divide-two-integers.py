class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        # Handle overflow: the result is limited to a 32-bit signed integer range
        # [-2^31, 2^31 - 1]
        MAX_INT = 2**31 - 1
        MIN_INT = -2**31
        
        # Determine the sign of the result
        negative = (dividend < 0) ^ (divisor < 0)
        
        # Work with absolute values
        a, b = abs(dividend), abs(divisor)
        res = 0
        
        # Use bit manipulation to find the quotient
        # We find the largest multiple of 'b' that fits into 'a'
        for i in range(31, -1, -1):
            if (b << i) <= a:
                a -= (b << i)
                res += (1 << i)
        
        # Apply the sign
        res = -res if negative else res
        
        # Clamp the result to 32-bit signed integer limits
        return max(MIN_INT, min(res, MAX_INT))     