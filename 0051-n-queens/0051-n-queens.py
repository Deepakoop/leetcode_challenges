class Solution:
    def solveNQueens(self, n: int) -> list[list[str]]:
        res = []
        board = [['.' for _ in range(n)] for _ in range(n)]
        
        # Sets to keep track of columns and diagonals currently under attack
        cols = set()
        pos_diag = set() # (r + c) remains constant for / diagonals
        neg_diag = set() # (r - c) remains constant for \ diagonals

        def backtrack(r):
            # Base case: All queens placed successfully
            if r == n:
                res.append(["".join(row) for row in board])
                return

            for c in range(n):
                # If the current column or diagonals are already occupied, skip
                if c in cols or (r + c) in pos_diag or (r - c) in neg_diag:
                    continue

                # Place the queen
                board[r][c] = 'Q'
                cols.add(c)
                pos_diag.add(r + c)
                neg_diag.add(r - c)

                # Move to the next row
                backtrack(r + 1)

                # Backtrack: Remove the queen and reset the sets
                board[r][c] = '.'
                cols.remove(c)
                pos_diag.remove(r + c)
                neg_diag.remove(r - c)

        backtrack(0)
        return res