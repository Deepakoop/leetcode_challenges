class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        n = len(s)
        segments = []
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            segments.append((s[i], j - i))
            i = j
            
        total_ones = sum(length for char, length in segments if char == '1')
        
        zeros = []
        if segments[0][0] == '1':
            zeros.append(0)
            
        for char, length in segments:
            if char == '0':
                zeros.append(length)
                
        if segments[-1][0] == '1':
            zeros.append(0)
            
        if len(zeros) < 2:
            return total_ones
            
        max_gain = 0
        for k in range(len(zeros) - 1):
            max_gain = max(max_gain, zeros[k] + zeros[k + 1])
            
        has_tradeable_one = False
        for idx in range(1, len(segments) - 1):
            if segments[idx][0] == '1':
                has_tradeable_one = True
                break
                
        if not has_tradeable_one:
            if len(segments) == 3 and segments[0][0] == '0' and segments[1][0] == '1' and segments[2][0] == '0':
                has_tradeable_one = True
                
        if not has_tradeable_one:
            return total_ones
            
        return total_ones + max_gain