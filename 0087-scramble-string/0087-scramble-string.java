import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Boolean> mem = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        
        String key = s1 + "+" + s2;
        if (mem.containsKey(key)) {
            return mem.get(key);
        }
        
        int[] count = new int[128];
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        for (int c : count) {
            if (c != 0) {
                mem.put(key, false);
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                mem.put(key, true);
                return true;
            }
            
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                mem.put(key, true);
                return true;
            }
        }
        
        mem.put(key, false);
        return false;
    }
}