import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        
        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }
        
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        maxHeap.offer(0); 
        
        int prevMaxHeight = 0;
        List<List<Integer>> result = new ArrayList<>();
        
        for (int[] event : events) {
            int xCoord = event[0];
            int height = event[1];
            
            if (height < 0) {
                maxHeap.offer(-height);
            } else {
                maxHeap.remove(height);
            }
            
            int currentMaxHeight = maxHeap.peek();
            
            if (currentMaxHeight != prevMaxHeight) {
                result.add(Arrays.asList(xCoord, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }
        
        return result;
    }
}