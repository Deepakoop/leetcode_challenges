class Solution:
    def isValid(self, s: str) -> bool:
        # Mapping of closing brackets to their corresponding opening brackets
        mapping = {')': '(', '}': '{', ']': '['}
        stack = []
        
        for char in s:
            # If the character is a closing bracket
            if char in mapping:
                # Pop the top element if stack is not empty, else assign a dummy value
                top_element = stack.pop() if stack else '#'
                
                # Check if the popped opening bracket matches the mapping
                if mapping[char] != top_element:
                    return False
            else:
                # It's an opening bracket, push onto stack
                stack.append(char)
        
        # If stack is empty, return True; otherwise False
        return not stack