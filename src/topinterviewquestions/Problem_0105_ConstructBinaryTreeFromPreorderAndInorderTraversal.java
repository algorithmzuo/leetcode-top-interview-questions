package topinterviewquestions;

import java.util.HashMap;

public class Problem_0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
	}

	public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> map) {
		if (L1 > R1) {
			return null;
		}
		TreeNode head = new TreeNode(pre[L1]);
		if (L1 == R1) {
			return head;
		}
		int findIndex = map.get(pre[L1]);
		head.left = f(pre, L1 + 1, L1 + findIndex - L2, in, L2, findIndex - 1, map);
		head.right = f(pre, L1 + findIndex - L2 + 1, R1, in, findIndex + 1, R2, map);
		return head;
	}

}
