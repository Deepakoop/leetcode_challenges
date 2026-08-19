import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowToSeats = new HashMap<>();
        
        for (int[] reserved : reservedSeats) {
            int row = reserved[0];
            int seat = reserved[1];
            rowToSeats.put(row, rowToSeats.getOrDefault(row, 0) | (1 << (seat - 1)));
        }
        
        int ans = 0;
        
        for (int seats : rowToSeats.values()) {
            boolean leftFree = (seats & 0b0111100000) == 0;
            boolean rightFree = (seats & 0b0000011110) == 0;
            boolean middleFree = (seats & 0b0001111000) == 0;
            
            if (leftFree && rightFree) {
                ans += 2;
            } else if (leftFree || rightFree || middleFree) {
                ans += 1;
            }
        }
        
        ans += (n - rowToSeats.size()) * 2;
        
        return ans;
    }
}