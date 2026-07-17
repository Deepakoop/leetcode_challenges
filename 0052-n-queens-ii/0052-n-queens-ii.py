class Solution:
    def totalNQueens(self, n: int) -> int:
        count = 0
        
        # We track columns and diagonals that are blocked
        cols = set()
        pos_diag = set() # (row + col)
        neg_diag = set() # (row - col)
        
        def backtrack(r):
            nonlocal count
            if r == n:
                count += 1
                return
            
            for c in range(n):
                if c in cols or (r + c) in pos_diag or (r - c) in neg_diag:
                    continue
                
                # Place queen
                cols.add(c)
                pos_diag.add(r + c)
                neg_diag.add(r - c)
                
                # Move to next row
                backtrack(r + 1)
                
                # Backtrack: Remove queen
                cols.remove(c)
                pos_diag.remove(r + c)
                neg_diag.remove(r - c)
        
        backtrack(0)
        return count     