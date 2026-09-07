class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long newCount = (total + 1 - dp[idx] + MOD) % MOD;
            dp[idx] = (dp[idx] + newCount) % MOD;
            total = (total + newCount) % MOD;
        }
        
        return (int) total;
    }
}