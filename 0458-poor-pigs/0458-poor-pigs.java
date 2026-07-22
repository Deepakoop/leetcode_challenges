class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int tests = minutesToTest / minutesToDie;
        int states = tests + 1;
        
        int pigs = 0;
        while (Math.pow(states, pigs) < buckets) {
            pigs++;
        }
        
        return pigs;
    }
}