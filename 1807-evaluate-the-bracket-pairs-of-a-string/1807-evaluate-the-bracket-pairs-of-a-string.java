import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                
                sb.append(map.getOrDefault(key, "?"));
                
                i = j;
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}