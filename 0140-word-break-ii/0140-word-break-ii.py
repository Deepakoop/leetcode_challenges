from functools import lru_cache

class Solution:
    def wordBreak(self, s: str, wordDict: list[str]) -> list[str]:
        word_set = set(wordDict)
        
        @lru_cache(None)
        def dfs(remaining_s: str) -> list[str]:
            if not remaining_s:
                return [""]
            
            res = []
            for i in range(1, len(remaining_s) + 1):
                prefix = remaining_s[:i]
                if prefix in word_set:
                    suffixes = dfs(remaining_s[i:])
                    for suffix in suffixes:
                        if suffix == "":
                            res.append(prefix)
                        else:
                            res.append(prefix + " " + suffix)
            return res
        
        return dfs(s)