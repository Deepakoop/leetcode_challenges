class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        n = len(s)
        m = len(p)
        
        # Create a DP table with dimensions (n+1) x (m+1)
        dp = [[False] * (m + 1) for _ in range(n + 1)]
        
        # Base case: empty string matches empty pattern
        dp[0][0] = True
        
        # Handle patterns starting with '*' that can match an empty string
        for j in range(1, m + 1):
            if p[j - 1] == '*':
                dp[0][j] = dp[0][j - 1]
        
        # Fill the DP table
        for i in range(1, n + 1):
            for j in range(1, m + 1):
                if p[j - 1] == '?' or p[j - 1] == s[i - 1]:
                    # Current characters match or pattern has '?'
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    # Two cases for '*':
                    # 1. dp[i][j-1]: '*' matches zero characters
                    # 2. dp[i-1][j]: '*' matches one or more characters
                    dp[i][j] = dp[i][j - 1] or dp[i - 1][j]
        
        return dp[n][m]     