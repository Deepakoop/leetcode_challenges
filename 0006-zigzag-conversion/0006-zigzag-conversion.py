class Solution:
    def convert(self, s: str, numRows: int) -> str:
        # Edge case: No change needed
        if numRows == 1 or numRows >= len(s):
            return s
        
        # Initialize a list of strings for each row
        rows = [""] * numRows
        current_row = 0
        going_down = False
        
        # Traverse the string
        for char in s:
            rows[current_row] += char
            
            # If we hit top or bottom, reverse direction
            if current_row == 0 or current_row == numRows - 1:
                going_down = not going_down
            
            # Update current row index
            current_row += 1 if going_down else -1
            
        # Combine all rows
        return "".join(rows)