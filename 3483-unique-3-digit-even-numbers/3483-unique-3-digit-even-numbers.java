class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        
        int uniqueCount = 0;
        
        for (int num = 100; num < 1000; num += 2) {
            int h = num / 100;
            int t = (num / 10) % 10;
            int u = num % 10;
            if (currentCountValid(h, t, u, count)) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
    
    private boolean currentCountValid(int h, int t, int u, int[] count) {
        int[] req = new int[10];
        req[h]++;
        req[t]++;
        req[u]++;
        
        for (int i = 0; i < 10; i++) {
            if (req[i] > count[i]) {
                return false;
            }
        }
        return true;
    }
}