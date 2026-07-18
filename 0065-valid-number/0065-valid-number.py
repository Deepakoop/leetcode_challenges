class Solution:
    def isNumber(self, s: str) -> bool:
        seen_digit = False
        seen_dot = False
        seen_e = False
        
        for i, char in enumerate(s):
            if char.isdigit():
                seen_digit = True
            elif char in "+-":
                if i > 0 and s[i - 1] not in "eE":
                    return False
            elif char in "eE":
                if seen_e or not seen_digit:
                    return False
                seen_e = True
                seen_digit = False
            elif char == ".":
                if seen_dot or seen_e:
                    return False
                seen_dot = True
            else:
                return False
                
        return seen_digit