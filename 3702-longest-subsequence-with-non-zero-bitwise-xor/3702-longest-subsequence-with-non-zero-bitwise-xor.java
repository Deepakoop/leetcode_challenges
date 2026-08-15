class Solution {
    public int longestSubsequence(int[] nums) {
        int xorSum = 0;
        boolean hasNonZero = false;
        
        for (int num : nums) {
            xorSum ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        
        if (!hasNonZero) {
            return 0;
        }
        
        if (xorSum != 0) {
            return nums.length;
        }
        
        return nums.length - 1;
    }
}