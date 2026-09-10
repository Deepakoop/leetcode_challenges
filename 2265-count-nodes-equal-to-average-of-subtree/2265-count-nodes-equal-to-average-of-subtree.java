class Solution {
    private int matchingNodes = 0;

    public int averageOfSubtree(TreeNode root) {
        postOrder(root);
        return matchingNodes;
    }

    private int[] postOrder(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = postOrder(node.left);
        int[] right = postOrder(node.right);

        int currentSum = node.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        if (currentSum / currentCount == node.val) {
            matchingNodes++;
        }

        return new int[]{currentSum, currentCount};
    }
}