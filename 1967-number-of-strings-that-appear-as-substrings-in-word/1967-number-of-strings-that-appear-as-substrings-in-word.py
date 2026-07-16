class Solution:
    def numOfStrings(self, patterns: list[str], word: str) -> int:
        count = 0
        
        # Iterate through each pattern in the list
        for p in patterns:
            # Check if the pattern exists inside the target word
            if p in word:
                count += 1
                
        return count