package topinterviewquestions;

public class Problem_0116_PopulatingNextRightPointersInEachNode {

	public static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;
	}

	public static class MyQueue {
		public Node head;
		public Node tail;

		public MyQueue() {
			head = null;
			tail = null;
		}

		public boolean isEmpty() {
			return head == null;
		}

		public void offer(Node cur) {
			if (head == null) {
				head = cur;
				tail = cur;
			} else {
				tail.next = cur;
				tail = cur;
			}
		}

		public Node poll() {
			Node ans = head;
			head = head.next;
			ans.next = null;
			return ans;
		}

	}

	public static Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Node curLevelEnd = root;
		Node nextLevelEnd = null;
		Node pre = null;
		MyQueue queue = new MyQueue();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (pre != null) {
				pre.next = cur;
			}
			pre = cur;
			if (cur.left != null) {
				queue.offer(cur.left);
				nextLevelEnd = cur.left;
			}
			if (cur.right != null) {
				queue.offer(cur.right);
				nextLevelEnd = cur.right;
			}
			if (cur == curLevelEnd) {
				curLevelEnd = nextLevelEnd;
				pre = null;
			}
		}
		return root;
	}

}
