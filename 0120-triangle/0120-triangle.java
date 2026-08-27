import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                int currentVal = triangle.get(row).get(col);
                int lowerLeft = triangle.get(row + 1).get(col);
                int lowerRight = triangle.get(row + 1).get(col + 1);
                
                triangle.get(row).set(col, currentVal + Math.min(lowerLeft, lowerRight));
            }
        }
        
        return triangle.get(0).get(0);
    }
}