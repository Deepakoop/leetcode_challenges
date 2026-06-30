class Solution {
public:
    string shortestPalindrome(string s) {
        if (s.empty()) return s;
        
        string rev = s;
        reverse(rev.begin(), rev.end());
        string l = s + "#" + rev;
        
        int n = l.size();
        vector<int> lps(n, 0);
        
        for (int i = 1; i < n; ++i) {
            int j = lps[i - 1];
            while (j > 0 && l[i] != l[j]) {
                j = lps[j - 1];
            }
            if (l[i] == l[j]) {
                j++;
            }
            lps[i] = j;
        }
        
        int longest_palette_len = lps.back();
        
        return rev.substr(0, s.size() - longest_palette_len) + s;
    }
};