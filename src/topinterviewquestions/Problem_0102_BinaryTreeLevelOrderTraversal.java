package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem_0102_BinaryTreeLevelOrderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		ans.add(new ArrayList<Integer>());
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		TreeNode curEnd = root; // 当前层，最右节点是谁
		TreeNode nextEnd = null; // 下一层，最右节点是谁
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			ans.get(ans.size() - 1).add(cur.val);
			if (cur.left != null) {
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			if (cur == curEnd) {
				curEnd = nextEnd;
				ans.add(new ArrayList<Integer>());
			}
		}
		ans.remove(ans.size() - 1);
		return ans;
	}

}
