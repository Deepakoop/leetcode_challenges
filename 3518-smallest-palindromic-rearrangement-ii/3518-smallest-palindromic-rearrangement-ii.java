import java.util.Arrays;

class Solution {
    private static final long MAX = 1000001L;

    public String smallestPalindrome(String s, long k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        for (int freq : count) {
            if (freq % 2 != 0) oddCount++;
        }
        if (oddCount > 1) return "";

        int[] halfCount = new int[26];
        char midLetter = '\0';
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            if (count[i] % 2 != 0) {
                midLetter = (char) (i + 'a');
            }
        }
        if (countArrangements(halfCount) < k) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        int halfLen = s.length() / 2;

        for (int step = 0; step < halfLen; step++) {
            for (int i = 0; i < 26; i++) {
                if (halfCount[i] == 0) continue;
                
                halfCount[i]--;
                long arrangements = countArrangements(halfCount);
                
                if (arrangements >= k) {
                    leftHalf.append((char) (i + 'a'));
                    break;
                } else {
                    k -= arrangements;
                    halfCount[i]++;
                }
            }
        }

        StringBuilder result = new StringBuilder(leftHalf);
        if (midLetter != '\0') {
            result.append(midLetter);
        }
        result.append(leftHalf.reverse());

        return result.toString();
    }

    private long countArrangements(int[] count) {
        int total = 0;
        for (int freq : count) total += freq;

        long res = 1;
        for (int freq : count) {
            res = multiplySafely(res, nCk(total, freq));
            if (res >= MAX) return MAX;
            total -= freq;
        }
        return res;
    }

    private long nCk(int n, int k) {
        long res = 1;
        for (int i = 1; i <= Math.min(k, n - k); i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX) return MAX;
        }
        return res;
    }

    private long multiplySafely(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (MAX / a < b) return MAX;
        return Math.min(MAX, a * b);
    }
}