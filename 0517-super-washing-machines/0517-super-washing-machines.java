class Solution {
    public int findMinMoves(int[] machines) {
        int totalDresses = 0;
        for (int m : machines) {
            totalDresses += m;
        }
        
        int n = machines.length;
        if (totalDresses % n != 0) {
            return -1;
        }
        
        int target = totalDresses / n;
        int minMoves = 0;
        int balance = 0;
        
        for (int m : machines) {
            balance += m - target;
            // The maximum moves required will be the max of:
            // 1. The absolute net balance of dresses flowing through this point (Math.abs(balance))
            // 2. The dresses that need to be pushed out of the current machine (m - target)
            minMoves = Math.max(minMoves, Math.max(Math.abs(balance), m - target));
        }
        
        return minMoves;
    }
}