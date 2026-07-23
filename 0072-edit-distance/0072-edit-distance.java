class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // DP table to store the minimum operations
        int[][] dp = new int[m + 1][n + 1];
        
        // Base cases: if one string is empty, the operations equal the length of the other string
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match, no operation needed
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Minimum of Insert, Delete, and Replace operations + 1
                    int insertOp = dp[i][j - 1];
                    int deleteOp = dp[i - 1][j];
                    int replaceOp = dp[i - 1][j - 1];
                    
                    dp[i][j] = 1 + Math.min(insertOp, Math.min(deleteOp, replaceOp));
                }
            }
        }
        
        return dp[m][n];
    }
}