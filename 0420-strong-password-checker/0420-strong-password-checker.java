class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int missing = getMissing(password);
        
        int replaces = 0;
        int oneSeq = 0;   // lengths with length % 3 == 0
        int twoSeq = 0;   // lengths with length % 3 == 1
        int threeSeq = 0; // lengths with length % 3 == 2
        
        for (int i = 2; i < n; ) {
            if (password.charAt(i) == password.charAt(i - 1) && password.charAt(i - 1) == password.charAt(i - 2)) {
                int length = 2;
                while (i < n && password.charAt(i) == password.charAt(i - 1)) {
                    length++;
                    i++;
                }
                replaces += length / 3;
                if (length % 3 == 0) oneSeq++;
                else if (length % 3 == 1) twoSeq++;
                else threeSeq++;
            } else {
                i++;
            }
        }
        
        if (n < 6) {
            return Math.max(6 - n, missing);
        }
        
        if (n <= 20) {
            return Math.max(replaces, missing);
        }
        
        int deletes = n - 20;
        
        // Deletions optimization for lengths
        replaces -= Math.min(oneSeq, deletes);
        deletes -= Math.min(oneSeq, deletes);
        
        replaces -= Math.min(Math.max(deletes, 0), twoSeq * 2) / 2;
        deletes -= Math.min(Math.max(deletes, 0), twoSeq * 2);
        
        replaces -= Math.max(deletes, 0) / 3;
        
        return (n - 20) + Math.max(replaces, missing);
    }
    
    private int getMissing(String password) {
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }
        int missing = 0;
        if (!hasLower) missing++;
        if (!hasUpper) missing++;
        if (!hasDigit) missing++;
        return missing;
    }
}