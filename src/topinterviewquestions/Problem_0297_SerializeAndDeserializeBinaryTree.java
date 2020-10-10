package topinterviewquestions;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_0297_SerializeAndDeserializeBinaryTree {

	// 提交代码时不要提交TreeNode类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}

	public String serialize(TreeNode root) {
		LinkedList<String> ans = new LinkedList<>();
		if (root == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(root.val));
			Queue<TreeNode> queue = new LinkedList<TreeNode>();
			queue.add(root);
			while (!queue.isEmpty()) {
				root = queue.poll();
				if (root.left != null) {
					ans.add(String.valueOf(root.left.val));
					queue.add(root.left);
				} else {
					ans.add(null);
				}
				if (root.right != null) {
					ans.add(String.valueOf(root.right.val));
					queue.add(root.right);
				} else {
					ans.add(null);
				}
			}
		}
		while (!ans.isEmpty() && ans.peekLast() == null) {
			ans.pollLast();
		}
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		String str = ans.pollFirst();
		builder.append(str == null ? "null" : str);
		while (!ans.isEmpty()) {
			str = ans.pollFirst();
			builder.append("," + (str == null ? "null" : str));
		}
		builder.append("]");
		return builder.toString();
	}

	public TreeNode deserialize(String data) {
		String[] strs = data.substring(1, data.length() - 1).split(",");
		int index = 0;
		TreeNode root = generateNode(strs[index++]);
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		if (root != null) {
			queue.add(root);
		}
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			node.left = generateNode(index == strs.length ? "null" : strs[index++]);
			node.right = generateNode(index == strs.length ? "null" : strs[index++]);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return root;
	}

	private TreeNode generateNode(String val) {
		if (val.equals("null")) {
			return null;
		}
		return new TreeNode(Integer.valueOf(val));
	}

}
