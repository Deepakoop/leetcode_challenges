import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();

        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftSubtrees = buildTrees(start, i - 1);

            List<TreeNode> rightSubtrees = buildTrees(i + 1, end);

            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode currRoot = new TreeNode(i);
                    currRoot.left = left;
                    currRoot.right = right;
                    allTrees.add(currRoot);
                }
            }
        }

        return allTrees;
    }
}