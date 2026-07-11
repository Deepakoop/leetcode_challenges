class Solution:
    def firstMissingPositive(self, nums: list[int]) -> int:
        n = len(nums)
        
        # Cyclic Sort: Place each number x at index x-1 if possible
        for i in range(n):
            # While the current number is in the range [1, n] 
            # and it is not at its correct position (nums[i]-1),
            # swap it to its correct position.
            while 1 <= nums[i] <= n and nums[nums[i] - 1] != nums[i]:
                # Swap nums[i] with the number at its target index
                correct_idx = nums[i] - 1
                nums[i], nums[correct_idx] = nums[correct_idx], nums[i]
        
        # After sorting, the first index i where nums[i] != i + 1
        # tells us that i + 1 is the missing positive.
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1
                
        # If all positions are correct, the missing number is n + 1
        return n + 1