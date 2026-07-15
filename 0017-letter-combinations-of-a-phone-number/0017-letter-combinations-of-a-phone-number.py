class Solution:
    def letterCombinations(self, digits: str) -> list[str]:
        if not digits:
            return []
        
        # Mapping of digits to letters
        phone_map = {
            "2": "abc", "3": "def", "4": "ghi", "5": "jkl",
            "6": "mno", "7": "pqrs", "8": "tuv", "9": "wxyz"
        }
        
        results = []
        
        def backtrack(index: int, current_string: str):
            # Base case: if current string is same length as input digits
            if len(current_string) == len(digits):
                results.append(current_string)
                return
            
            # Get letters for current digit
            possible_letters = phone_map[digits[index]]
            
            for char in possible_letters:
                backtrack(index + 1, current_string + char)
                
        backtrack(0, "")
        return results

# --- Example Usage ---
if __name__ == "__main__":
    solver = Solution()
    print(solver.letterCombinations("23"))
    # Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]