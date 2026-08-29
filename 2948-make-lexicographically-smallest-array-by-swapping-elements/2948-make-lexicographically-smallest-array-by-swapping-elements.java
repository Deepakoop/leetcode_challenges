import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));
        
        int[] res = new int[n];
        List<Integer> groupIndices = new ArrayList<>();
        List<Integer> groupValues = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[indices[i]] - nums[indices[i - 1]] > limit) {
                Collections.sort(groupIndices);
                for (int j = 0; j < groupIndices.size(); j++) {
                    res[groupIndices.get(j)] = groupValues.get(j);
                }
                groupIndices.clear();
                groupValues.clear();
            }
            groupIndices.add(indices[i]);
            groupValues.add(nums[indices[i]]);
        }
        
        Collections.sort(groupIndices);
        for (int j = 0; j < groupIndices.size(); j++) {
            res[groupIndices.get(j)] = groupValues.get(j);
        }
        
        return res;
    }
}