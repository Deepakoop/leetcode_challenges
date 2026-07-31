import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        
        Arrays.sort(count);
        
        int totalPushes = 0;
        
        for (int i = 0; i < 26; i++) {
            int frequency = count[26 - i - 1];
            if (frequency == 0) {
                break;
            }
            totalPushes += frequency * (i / 8 + 1);
        }
        
        return totalPushes;
    }
}