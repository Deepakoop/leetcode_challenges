import java.util.TreeMap;

class SummaryRanges {
    private TreeMap<Integer, int[]> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }
    
    public void addNum(int val) {
        if (intervals.containsKey(val)) {
            return;
        }
        
        Integer lo = intervals.lowerKey(val);
        Integer hi = intervals.higherKey(val);
        
        if (lo != null && hi != null && intervals.get(lo)[1] + 1 == val && val + 1 == hi) {
            intervals.get(lo)[1] = intervals.get(hi)[1];
            intervals.remove(hi);
        } else if (lo != null && intervals.get(lo)[1] + 1 >= val) {
            intervals.get(lo)[1] = Math.max(intervals.get(lo)[1], val);
        } else if (hi != null && val + 1 == hi) {
            intervals.put(val, new int[] { val, intervals.get(hi)[1] });
            intervals.remove(hi);
        } else {
            intervals.put(val, new int[] { val, val });
        }
    }
    
    public int[][] getIntervals() {
        return intervals.values().toArray(new int[intervals.size()][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_index = new int[][];
 * param_index = obj.getIntervals();
 */