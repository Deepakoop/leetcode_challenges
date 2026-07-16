class Solution:
    def removeElement(self, nums: list[int], val: int) -> int:
        # 'i' pointer track karta hai ki naya unique element kahan place karna hai
        i = 0
        
        # Array ko loop ke zariye traverse karein
        for j in range(len(nums)):
            # Agar current element 'val' ke barabar nahi hai
            if nums[j] != val:
                # Toh ise 'i' wali position par copy kar dein
                nums[i] = nums[j]
                i += 1
        
        # 'i' woh count hai jo represent karta hai kitne elements 'val' ke equal nahi hain
        return i