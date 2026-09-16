class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1000000007;
        int totalPoints = n + k - 1;
        int r = 2 * k;
        
        return (int) combination(totalPoints, r, MOD);
    }
    
    private long combination(int n, int r, long mod) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;
        
        long[] dp = new long[r + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, r); j > 0; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % mod;
            }
        }
        
        return dp[r];
    }
}