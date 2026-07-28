import java.util.*;

class Solution {
    private List<List<String>> ans;
    private Map<String, List<String>> prev;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        ans = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        
        if (!words.contains(endWord)) {
            return ans;
        }
        
        words.remove(beginWord);
        
        Map<String, Integer> dist = new HashMap<>();
        dist.put(beginWord, 0);
        
        prev = new HashMap<>();
        
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        
        boolean found = false;
        int step = 0;
        
        while (!q.isEmpty() && !found) {
            ++step;
            int size = q.size();
            Set<String> visitedInThisLevel = new HashSet<>();
            
            for (int i = 0; i < size; i++) {
                String p = q.poll();
                char[] chars = p.toCharArray();
                
                for (int j = 0; j < chars.length; j++) {
                    char originalChar = chars[j];
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String t = new String(chars);
                        
                        if (t.equals(endWord)) {
                            found = true;
                        }
                        
                        if (dist.containsKey(t) && dist.get(t) == step) {
                            prev.computeIfAbsent(t, k -> new ArrayList<>()).add(p);
                        }
                        
                        if (words.contains(t)) {
                            words.remove(t);
                            q.offer(t);
                            dist.put(t, step);
                            prev.computeIfAbsent(t, k -> new ArrayList<>()).add(p);
                        }
                    }
                    chars[j] = originalChar;
                }
            }
        }
        
        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, beginWord, path);
        }
        
        return ans;
    }
    
    private void dfs(String cur, String beginWord, List<String> path) {
        if (cur.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            ans.add(validPath);
            return;
        }
        
        if (!prev.containsKey(cur)) {
            return;
        }
        
        for (String p : prev.get(cur)) {
            path.add(p);
            dfs(p, beginWord, path);
            path.remove(path.size() - 1);
        }
    }
}