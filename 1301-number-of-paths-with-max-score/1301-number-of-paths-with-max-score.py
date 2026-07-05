class Solution:
    def pathsWithMaxScore(self, board):
        MOD = 10**9 + 7
        n = len(board)
        
        # Initialize DP tables
        # dp_score stores the max score to reach (0,0) from (r,c)
        # dp_paths stores the number of paths to achieve that max score
        dp_score = [[0] * n for _ in range(n)]
        dp_paths = [[0] * n for _ in range(n)]
        
        # Base case: The destination 'E' at (0,0)
        dp_paths[0][0] = 1 
        
        # Iterate through the board from top-left to bottom-right
        for r in range(n):
            for c in range(n):
                if (r == 0 and c == 0) or board[r][c] == 'X':
                    continue
                
                max_prev_score = -1
                paths_to_max = 0
                
                # Check the three potential incoming directions
                # 1. From Up (r-1, c)
                if r > 0 and board[r-1][c] != 'X' and dp_paths[r-1][c] > 0:
                    if dp_score[r-1][c] > max_prev_score:
                        max_prev_score = dp_score[r-1][c]
                        paths_to_max = dp_paths[r-1][c]
                    elif dp_score[r-1][c] == max_prev_score:
                        paths_to_max = (paths_to_max + dp_paths[r-1][c]) % MOD
                
                # 2. From Left (r, c-1)
                if c > 0 and board[r][c-1] != 'X' and dp_paths[r][c-1] > 0:
                    if dp_score[r][c-1] > max_prev_score:
                        max_prev_score = dp_score[r][c-1]
                        paths_to_max = dp_paths[r][c-1]
                    elif dp_score[r][c-1] == max_prev_score:
                        paths_to_max = (paths_to_max + dp_paths[r][c-1]) % MOD
                        
                # 3. From Diagonal Up-Left (r-1, c-1)
                if r > 0 and c > 0 and board[r-1][c-1] != 'X' and dp_paths[r-1][c-1] > 0:
                    if dp_score[r-1][c-1] > max_prev_score:
                        max_prev_score = dp_score[r-1][c-1]
                        paths_to_max = dp_paths[r-1][c-1]
                    elif dp_score[r-1][c-1] == max_prev_score:
                        paths_to_max = (paths_to_max + dp_paths[r-1][c-1]) % MOD
                
                # If at least one valid incoming path exists
                if max_prev_score != -1:
                    current_val = int(board[r][c]) if board[r][c] != 'S' else 0
                    dp_score[r][c] = max_prev_score + current_val
                    dp_paths[r][c] = paths_to_max
                    
        # The result for the starting square 'S' is at (n-1, n-1)
        return [dp_score[n-1][n-1], dp_paths[n-1][n-1]]