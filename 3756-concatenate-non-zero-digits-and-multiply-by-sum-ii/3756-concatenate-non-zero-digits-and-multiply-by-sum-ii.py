from typing import List

class Solution:
    def sumAndMultiply(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        MOD = 10**9 + 7
        
        # Precompute powers of 10 modulo 10^9 + 7
        pow10 = [1] * (n + 1)
        for i in range(1, n + 1):
            pow10[i] = (pow10[i - 1] * 10) % MOD
            
        # Prefix arrays
        sum_d = [0] * (n + 1)   # Prefix sum of digits
        cnt_n0 = [0] * (n + 1)  # Prefix count of non-zero digits
        p = [0] * (n + 1)       # Prefix value of non-zero digit concatenation
        
        for i, char in enumerate(s, 1):
            d = int(char)
            sum_d[i] = sum_d[i - 1] + d
            
            if d > 0:
                cnt_n0[i] = cnt_n0[i - 1] + 1
                p[i] = (p[i - 1] * 10 + d) % MOD
            else:
                cnt_n0[i] = cnt_n0[i - 1]
                p[i] = p[i - 1]
                
        ans = []
        for l, r in queries:
            # 1. Total digit sum in the range
            sd = sum_d[r + 1] - sum_d[l]
            
            # 2. Total non-zero digits in the range
            n0 = cnt_n0[r + 1] - cnt_n0[l]
            
            # 3. Extract the concatenated number x modulo MOD
            x = (p[r + 1] - p[l] * pow10[n0] % MOD + MOD) % MOD
            
            # 4. Multiply by sum and append to answer
            ans.append((x * sd) % MOD)
            
        return ans        