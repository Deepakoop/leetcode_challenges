import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        
        int maxPointsOnLine = 1;
        
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicates = 0;
            int currentMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }
                
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = Math.abs(dy);
                }
                
                String slopeKey = dx + "," + dy;
                int count = slopeMap.getOrDefault(slopeKey, 0) + 1;
                slopeMap.put(slopeKey, count);
                currentMax = Math.max(currentMax, count);
            }
            
            maxPointsOnLine = Math.max(maxPointsOnLine, currentMax + duplicates + 1);
        }
        
        return maxPointsOnLine;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}