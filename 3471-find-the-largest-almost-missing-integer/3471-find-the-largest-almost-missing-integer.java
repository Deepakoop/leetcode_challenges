class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        int[] count = new int[51]; 
        
        for (int num : nums) {
            count[num]++;
        }
        
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                max = Math.max(max, num);
            }
            return max;
        }
        
        if (k == 1) {
            int max = -1;
            for (int num : nums) {
                if (count[num] == 1) {
                    max = Math.max(max, num);
                }
            }
            return max;
        }
        
        int max = -1;
        
        if (count[nums[0]] == 1) {
            max = Math.max(max, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            max = Math.max(max, nums[n - 1]);
        }
        
        return max;
    }
}