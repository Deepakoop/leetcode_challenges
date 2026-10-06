class Solution {
    public int minAddToMakeValid(String s) {
        int open = 0;
        int minAdd = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    minAdd++;
                }
            }
        }
        return minAdd + open;
    }
}