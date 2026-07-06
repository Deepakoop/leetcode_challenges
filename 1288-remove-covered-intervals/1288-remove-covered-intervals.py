class Solution:
    def removeCoveredIntervals(self, intervals):
        # Sort by start ascending. If starts are equal, sort by end descending.
        intervals.sort(key=lambda x: (x[0], -x[1]))
        
        count = 0
        max_end = 0
        
        for start, end in intervals:
            # If the current interval's end is greater than the max_end seen so far,
            # it is NOT covered. We keep it and update max_end.
            if end > max_end:
                count += 1
                max_end = end
                
        return count 