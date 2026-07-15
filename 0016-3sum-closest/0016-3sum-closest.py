class Solution:
    def threeSumClosest(self, nums: list[int], target: int) -> int:
        nums.sort()
        # Initialize with the sum of the first three elements
        closest_sum = nums[0] + nums[1] + nums[2]
        
        for i in range(len(nums) - 2):
            left, right = i + 1, len(nums) - 1
            
            while left < right:
                current_sum = nums[i] + nums[left] + nums[right]
                
                # Check if current_sum is closer to target
                if abs(target - current_sum) < abs(target - closest_sum):
                    closest_sum = current_sum
                
                # Adjust pointers based on comparison with target
                if current_sum < target:
                    left += 1
                elif current_sum > target:
                    right -= 1
                else:
                    # If exact match, return immediately
                    return current_sum
                    
        return closest_sum

# --- Example Usage ---
if __name__ == "__main__":
    solver = Solution()
    print(solver.threeSumClosest([-1, 2, 1, -4], 1))
    # Output: 2 (-1 + 2 + 1 = 2)