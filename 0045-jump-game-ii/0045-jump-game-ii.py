class Solution:
    def jump(self, nums: list[int]) -> int:
        n = len(nums)
        if n <= 1:
            return 0
        
        jumps = 0
        current_end = 0
        farthest = 0
        
        # We iterate up to n - 2 because we don't need to jump 
        # once we are already at the last index
        for i in range(n - 1):
            # Update the farthest index reachable from the current position
            farthest = max(farthest, i + nums[i])
            
            # If we have reached the end of the current jump range
            if i == current_end:
                jumps += 1
                current_end = farthest
                
                # Optimization: if we can already reach the last index, we can stop
                if current_end >= n - 1:
                    break
                    
        return jumps