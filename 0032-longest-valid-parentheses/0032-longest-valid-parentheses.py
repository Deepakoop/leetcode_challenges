class Solution:
    def longestValidParentheses(self, s: str) -> int:
        max_len = 0
        # Start with -1 to handle length calculation for full-string matches
        stack = [-1]
        
        for i, char in enumerate(s):
            if char == '(':
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    # New base for next potential valid substring
                    stack.append(i)
                else:
                    # Length = current index - index of the last unmatched '('
                    max_len = max(max_len, i - stack[-1])
                    
        return max_len   