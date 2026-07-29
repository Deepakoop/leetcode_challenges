import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int i, int[] nums, List<Integer> currentSubset, List<List<Integer>> result) {
        if (i == nums.length) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        currentSubset.add(nums[i]);
        backtrack(i + 1, nums, currentSubset, result);
        currentSubset.remove(currentSubset.size() - 1);
        backtrack(i + 1, nums, currentSubset, result);
    }
}