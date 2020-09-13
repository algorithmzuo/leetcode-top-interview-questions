package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		LinkedList<TreeNode> deque = new LinkedList<>();
		deque.add(root);
		int size = 0;
		while(!deque.isEmpty()) {
			size = deque.size();
			List<Integer> curLevel = new ArrayList<>();
			for(int i = 0 ; i< size;i++) {
				TreeNode cur = deque.pollLast();
				curLevel.add(cur.val);
				if(cur.left != null) {
					deque.addFirst(cur.left);
				}
				if(cur.right != null) {
					deque.addFirst(cur.right);
				}
			}
			ans.add(curLevel);
		}
		return ans;
	}

}
