class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        boolean[] pairXors = new boolean[2048];
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairXors[nums[i] ^ nums[j]] = true;
            }
        }
        
        boolean[] finalXors = new boolean[2048];
        int uniqueCount = 0;
        
        for (int val = 0; val < 2048; val++) {
            if (!pairXors[val]) continue;
            
            for (int k = 0; k < n; k++) {
                int res = val ^ nums[k];
                if (!finalXors[res]) {
                    finalXors[res] = true;
                    uniqueCount++;
                }
            }
        }
        
        return uniqueCount;
    }
}