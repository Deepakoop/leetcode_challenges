import java.util.Arrays;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        char[] sChars = s.toCharArray();
        Arrays.sort(sChars);
        
        int[] count = new int[26];
        for (char c : sChars) {
            count[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        if (dfs(0, true, count, target, sb, n)) {
            return sb.toString();
        }
        
        return "";
    }
    
    private boolean dfs(int index, boolean isTight, int[] count, String target, StringBuilder sb, int n) {
        if (index == n) {
            return !isTight;
        }
        
        int startChar = isTight ? (target.charAt(index) - 'a') : 0;
        
        for (int c = startChar; c < 26; c++) {
            if (count[c] > 0) {
                count[c]--;
                sb.append((char) ('a' + c));
                
                boolean nextTight = isTight && (c == (target.charAt(index) - 'a'));
                
                if (!nextTight) {
                    for (int i = 0; i < 26; i++) {
                        while (count[i] > 0) {
                            sb.append((char) ('a' + i));
                            count[i]--;
                        }
                    }
                    return true;
                }
                
                if (dfs(index + 1, nextTight, count, target, sb, n)) {
                    return true;
                }
                
                sb.deleteCharAt(sb.length() - 1);
                count[c]++;
            }
        }
        return false;
    }
}