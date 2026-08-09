import java.util.Arrays;

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] memo = new int[n][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        int[] suffix = new int[n];
        suffix[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        
        return dp(suffix, 0, 1, memo);
    }
    
    private int dp(int[] suffix, int i, int M, int[][] memo) {
        if (i + 2 * M >= suffix.length) {
            return suffix[i];
        }
        
        if (memo[i][M] != -1) {
            return memo[i][M];
        }
        
        int maxStones = 0;
        
        for (int X = 1; X <= 2 * M; X++) {
            int opponentStones = dp(suffix, i + X, Math.max(M, X), memo);
            
            int currentStones = suffix[i] - opponentStones;
            
            maxStones = Math.max(maxStones, currentStones);
        }
        
        return memo[i][M] = maxStones;
    }
}