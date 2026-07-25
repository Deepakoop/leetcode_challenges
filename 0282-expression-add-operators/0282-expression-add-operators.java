import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null || num.length() == 0) return ans;
        dfs(num, target, 0, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(String num, int target, int s, long prev, long eval, StringBuilder sb, List<String> ans) {
        if (s == num.length()) {
            if (eval == target) {
                ans.add(sb.toString());
            }
            return;
        }

        int length = sb.length();
        for (int i = s; i < num.length(); ++i) {
            if (i > s && num.charAt(s) == '0') {
                break;
            }

            long curr = Long.parseLong(num.substring(s, i + 1));

            if (s == 0) {
                sb.append(curr);
                dfs(num, target, i + 1, curr, curr, sb, ans);
                sb.setLength(length);
            } else {
                sb.append("+").append(curr);
                dfs(num, target, i + 1, curr, eval + curr, sb, ans);
                sb.setLength(length);

                sb.append("-").append(curr);
                dfs(num, target, i + 1, -curr, eval - curr, sb, ans);
                sb.setLength(length);

                sb.append("*").append(curr);
                dfs(num, target, i + 1, prev * curr, eval - prev + (prev * curr), sb, ans);
                sb.setLength(length);
            }
        }
    }
}