package topinterviewquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Problem_0218_TheSkylineProblem {

	public static class Node {
		public int x;
		public boolean isAdd;
		public int h;

		public Node(int x, boolean isAdd, int h) {
			this.x = x;
			this.isAdd = isAdd;
			this.h = h;
		}
	}

	public static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.x != o2.x) {
				return o1.x - o2.x;
			}
			if (o1.isAdd != o2.isAdd) {
				return o1.isAdd ? -1 : 1;
			}
			return 0;
		}
	}

	public static List<List<Integer>> getSkyline(int[][] matrix) {
		Node[] nodes = new Node[matrix.length * 2];
		for (int i = 0; i < matrix.length; i++) {
			nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
			nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
		}
		Arrays.sort(nodes, new NodeComparator());
		// 有序表，key 代表某个高度 value 这个高度出现的次数
		TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
		// 有序表 key x的值 value 处在x位置时的高度
		TreeMap<Integer, Integer> xMaxHeight = new TreeMap<>();
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].isAdd) {
				if (!mapHeightTimes.containsKey(nodes[i].h)) {
					mapHeightTimes.put(nodes[i].h, 1);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
				}
			} else {
				if (mapHeightTimes.get(nodes[i].h) == 1) {
					mapHeightTimes.remove(nodes[i].h);
				} else {
					mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
				}
			}
			if (mapHeightTimes.isEmpty()) {
				xMaxHeight.put(nodes[i].x, 0);
			} else {
				xMaxHeight.put(nodes[i].x, mapHeightTimes.lastKey());
			}
		}
		List<List<Integer>> ans = new ArrayList<>();
		for (Entry<Integer, Integer> entry : xMaxHeight.entrySet()) {
			int curX = entry.getKey();
			int curMaxHeight = entry.getValue();
			if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
				ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
			}
		}
		return ans;
	}

}
