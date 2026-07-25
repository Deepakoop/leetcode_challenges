import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> ans = new HashSet<>();
        
        for (int i = 0; i <= s.length() - 10; i++) {
            String seq = s.substring(i, i + 10);
            if (seen.contains(seq)) {
                ans.add(seq);
            } else {
                seen.add(seq);
            }
        }
        
        return new ArrayList<>(ans);
    }
}