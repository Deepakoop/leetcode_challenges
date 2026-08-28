class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 != 0) {
                if (oddChar != -1) return "";
                oddChar = i;
            }
        }
        
        int[] leftCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            leftCnt[i] = cnt[i] / 2;
        }
        
        int[] left = new int[half];
        int[] remain = leftCnt.clone();
        
        boolean match = true;
        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';
            if (remain[c] > 0) {
                left[i] = c;
                remain[c]--;
            } else {
                match = false;
                break;
            }
        }
        
        if (match) {
            String candidate = buildPalindrome(left, remain, oddChar, n);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        for (int pos = half - 1; pos >= 0; pos--) {
            remain = leftCnt.clone();
            int[] tempLeft = new int[half];
            boolean ok = true;
            
            for (int i = 0; i < pos; i++) {
                int c = target.charAt(i) - 'a';
                if (remain[c] > 0) {
                    tempLeft[i] = c;
                    remain[c]--;
                } else {
                    ok = false;
                    break;
                }
            }
            
            if (!ok) continue;
            
            int targetChar = target.charAt(pos) - 'a';
            boolean found = false;
            for (int c = targetChar + 1; c < 26; c++) {
                if (remain[c] > 0) {
                    tempLeft[pos] = c;
                    remain[c]--;
                    found = true;
                    break;
                }
            }
            
            if (!found) continue;
            
            for (int i = pos + 1; i < half; i++) {
                for (int c = 0; c < 26; c++) {
                    if (remain[c] > 0) {
                        tempLeft[i] = c;
                        remain[c]--;
                        break;
                    }
                }
            }
            
            String candidate = buildPalindrome(tempLeft, remain, oddChar, n);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        return "";
    }
    
    private String buildPalindrome(int[] left, int[] remain, int oddChar, int n) {
        int half = n / 2;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < half; i++) {
            sb.append((char) (left[i] + 'a'));
        }
        
        if (n % 2 == 1) {
            sb.append((char) (oddChar + 'a'));
        }
        
        for (int i = half - 1; i >= 0; i--) {
            sb.append((char) (left[i] + 'a'));
        }
        
        return sb.toString();
    }
}