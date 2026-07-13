class Solution:
    def isPalindrome(self, x: int) -> bool:
        # Negative numbers are not palindromes (e.g., -121 != 121-)
        # Numbers ending in 0 (except 0 itself) are not palindromes
        if x < 0 or (x % 10 == 0 and x != 0):
            return False
        
        reverted_number = 0
        while x > reverted_number:
            # Get the last digit and add it to the reversed portion
            reverted_number = (reverted_number * 10) + (x % 10)
            # Remove the last digit from the original number
            x //= 10
            
        # For even length numbers: x == reverted_number
        # For odd length numbers: x == reverted_number // 10 (ignores the middle digit)
        return x == reverted_number or x == reverted_number // 10    