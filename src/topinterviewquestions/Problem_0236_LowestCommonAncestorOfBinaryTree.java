package topinterviewquestions;

public class Problem_0236_LowestCommonAncestorOfBinaryTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static TreeNode lowestCommonAncestor(TreeNode head, TreeNode o1, TreeNode o2) {
		return process(head, o1, o2).ans;
	}

	public static class Info {
		public TreeNode ans;
		public boolean findO1;
		public boolean findO2;

		public Info(TreeNode a, boolean f1, boolean f2) {
			ans = a;
			findO1 = f1;
			findO2 = f2;
		}
	}

	public static Info process(TreeNode X, TreeNode o1, TreeNode o2) {
		if (X == null) {
			return new Info(null, false, false);
		}
		Info leftInfo = process(X.left, o1, o2);
		Info rightInfo = process(X.right, o1, o2);
		boolean findO1 = X == o1 || leftInfo.findO1 || rightInfo.findO1;
		boolean findO2 = X == o2 || leftInfo.findO2 || rightInfo.findO2;
		TreeNode ans = null;
		if (leftInfo.ans != null) {
			ans = leftInfo.ans;
		}
		if (rightInfo.ans != null) {
			ans = rightInfo.ans;
		}
		if (ans == null) {
			if (findO1 && findO2) {
				ans = X;
			}
		}
		return new Info(ans, findO1, findO2);
	}

}
