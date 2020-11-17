package com.算法.leetCode.offer.树;


import org.junit.jupiter.api.Test;

/**
 * 剑指 Offer 07. 重建二叉树
 *
 * @author pjliu
 */
public class Offer07 {
    @Test
    public void testBuildTree() {
        int[] pre = {1,2};
        int[] in = {2,1};
        TreeNode treeNode = buildTree(pre, in);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return deepBuildTree(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private TreeNode deepBuildTree(int[] preorder, int pStart, int[] inorder, int left, int right) {
        if (right - left < 0) return null;
        TreeNode root = new TreeNode(preorder[pStart]);
        for (int i = left; i < right; i++) {
            if (root.val == inorder[i]) {
                //左子树前序索引为:pStart+1
                //左子树中序左边界：没有变化（left）
                //左子树中序右边界：
                root.left = deepBuildTree(preorder, pStart + 1, inorder, left, i - 1);
                //右子树前序索引为:pStart+左子树元素个数+1；左子树元素个数=i-left
                //
                root.right = deepBuildTree(preorder, pStart + i - left + 1, inorder, i + 1, right);
            }
        }
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
