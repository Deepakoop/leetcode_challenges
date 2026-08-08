class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If the starting point has an obstacle, 0 paths are possible.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = 1; // Base case: starting point

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // Obstacle blocks all paths to this cell
                } else if (j > 0) {
                    dp[j] += dp[j - 1]; // Sum of paths from top and left
                }
            }
        }

        return dp[n - 1];
    }
}