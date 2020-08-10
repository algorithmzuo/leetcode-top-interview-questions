package topinterviewquestions;

/*
 * 提交时把类名、构造函数名从Problem_0208_Trie改为Trie
 * 
 * */
public class Problem_0208_Trie {

	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	private Node root;

	public Problem_0208_Trie() {
		root = new Node();
	}

	public void insert(String word) {
		if (word == null) {
			return;
		}
		char[] str = word.toCharArray();
		Node node = root;
		int path = 0;
		for (int i = 0; i < str.length; i++) {
			path = str[i] - 'a';
			if (node.nexts[path] == null) {
				node.nexts[path] = new Node();
			}
			node = node.nexts[path];
		}
		node.end = true;
	}

	public boolean search(String word) {
		if (word == null) {
			return false;
		}
		char[] chs = word.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i] - 'a';
			if (node.nexts[index] == null) {
				return false;
			}
			node = node.nexts[index];
		}
		return node.end;
	}

	public boolean startsWith(String pre) {
		if (pre == null) {
			return false;
		}
		char[] chs = pre.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i] - 'a';
			if (node.nexts[index] == null) {
				return false;
			}
			node = node.nexts[index];
		}
		return true;
	}

}
