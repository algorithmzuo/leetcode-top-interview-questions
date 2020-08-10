package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem_0103_BinaryTreeZigzagLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		ans.add(new ArrayList<>());
		Stack<TreeNode> LR = new Stack<>();
		Stack<TreeNode> RL = new Stack<>();
		LR.add(root);
		boolean era = true;
		while (!LR.isEmpty() || !RL.isEmpty()) {
			TreeNode cur = era ? LR.pop() : RL.pop();
			ans.get(ans.size() - 1).add(cur.val);
			if (era) {
				if (cur.left != null) {
					RL.push(cur.left);
				}
				if (cur.right != null) {
					RL.push(cur.right);
				}
			} else {
				if (cur.right != null) {
					LR.push(cur.right);
				}
				if (cur.left != null) {
					LR.push(cur.left);
				}
			}
			if (((era && LR.isEmpty()) || (!era && RL.isEmpty()))) {
				era = !era;
				ans.add(new ArrayList<>());
			}
		}
		ans.remove(ans.size() - 1);
		return ans;
	}

}
