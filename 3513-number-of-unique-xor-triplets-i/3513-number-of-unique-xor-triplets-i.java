class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        
        // Find the smallest power of 2 strictly greater than n, 
        // which gives the total number of unique XOR triplets for n >= 3.
        int ans = 1;
        while (ans <= n) {
            ans <<= 1;
        }
        return ans;
    }
}