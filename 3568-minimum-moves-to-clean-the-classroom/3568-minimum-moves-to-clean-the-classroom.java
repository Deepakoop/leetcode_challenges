import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = 0, startC = 0;
        int cnt = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startR = i;
                    startC = j;
                } else if (c == 'L') {
                    litterId[i][j] = cnt++;
                }
            }
        }
        
        if (cnt == 0) return 0;
        
        int initialMask = (1 << cnt) - 1;
        
        // Queue stores: {r, c, current_energy, mask, moves}
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << cnt];
        
        queue.offer(new int[]{startR, startC, energy, initialMask, 0});
        visited[startR][startC][energy][initialMask] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int curEnergy = curr[2];
            int mask = curr[3];
            int moves = curr[4];
            
            if (mask == 0) {
                return moves;
            }
            
            if (curEnergy == 0) continue;
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char nextChar = classroom[nr].charAt(nc);
                if (nextChar == 'X') continue;
                
                int nextEnergy = curEnergy - 1;
                int nextMask = mask;
                
                if (nextChar == 'R') {
                    nextEnergy = energy;
                } else if (nextChar == 'L') {
                    int id = litterId[nr][nc];
                    if ((mask & (1 << id)) != 0) {
                        nextMask = mask & ~(1 << id);
                    }
                }
                
                if (!visited[nr][nc][nextEnergy][nextMask]) {
                    visited[nr][nc][nextEnergy][nextMask] = true;
                    queue.offer(new int[]{nr, nc, nextEnergy, nextMask, moves + 1});
                }
            }
        }
        
        return -1;
    }
}