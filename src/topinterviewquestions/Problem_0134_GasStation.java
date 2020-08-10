package topinterviewquestions;

import java.util.LinkedList;

public class Problem_0134_GasStation {

	/*
	 * 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
	 */
	public static int canCompleteCircuit1(int[] gas, int[] cost) {
		int N = gas.length;
		int init = -1;
		for (int i = 0; i < N; i++) {
			gas[i] -= cost[i];
			if (gas[i] >= 0) {
				init = init == -1 ? i : init;
			}
		}
		if (init == -1) {
			return -1;
		}
		int[] all = new int[N << 1];
		int index = 0;
		all[index++] = gas[init];
		int start = (init < N - 1) ? (init + 1) : 0;
		while (start != init) {
			all[index++] = gas[start];
			start = (start < N - 1) ? (start + 1) : 0;
		}
		for (int i = 0; i < N; i++) {
			all[i + N] = all[i];
		}
		for (int i = 1; i < all.length; i++) {
			all[i] = all[i] + all[i - 1];
		}
		LinkedList<Integer> minQ = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			while (!minQ.isEmpty() && all[minQ.peekLast()] >= all[i]) {
				minQ.pollLast();
			}
			minQ.add(i);
		}
		if (all[minQ.peekFirst()] >= 0) {
			return init;
		}
		for (int i = 0; i < N; i++) {
			while (!minQ.isEmpty() && all[minQ.peekLast()] >= all[i + N]) {
				minQ.pollLast();
			}
			minQ.add(i + N);
			if (minQ.peekFirst() == i) {
				minQ.peekFirst();
			}
			if (all[minQ.peekFirst()] - all[i] >= 0) {
				return i + init + 1;
			}
		}
		return -1;
	}

	/*
	 * 这个方法的时间复杂度O(N)，额外空间复杂度O(1)
	 * 训练营讲了
	 */
	public static int canCompleteCircuit2(int[] oil, int[] dis) {
		int init = changeDisArrayGetInit(oil, dis);
		if (init != -1) {
			int ans = enlargeArea(dis, init);
			if (ans != -1) {
				return ans;
			}
		}
		return -1;
	}

	public static int changeDisArrayGetInit(int[] oil, int[] dis) {
		int init = -1;
		for (int i = 0; i < dis.length; i++) {
			dis[i] = oil[i] - dis[i];
			if (dis[i] >= 0) {
				init = i;
			}
		}
		return init;
	}

	public static int enlargeArea(int[] dis, int init) {
		int ans = -1;
		int start = init;
		int end = nextIndex(init, dis.length);
		int need = 0;
		int rest = 0;
		do {
			// 当前来到的start已经在连通区域中，可以确定后续的开始点一定无法转完一圈
			if (start != init && start == lastIndex(end, dis.length)) {
				break;
			}
			// 当前来到的start不在连通区域中，就扩充连通区域
			if (dis[start] < need) { // 当前start无法接到连通区的头部
				need -= dis[start];
			} else { // 当前start可以接到连通区的头部，开始扩充连通区域的尾巴
				rest += dis[start] - need;
				need = 0;
				while (rest >= 0 && end != start) {
					rest += dis[end];
					end = nextIndex(end, dis.length);
				}
				// 如果连通区域已经覆盖整个环，当前的start是良好出发点，进入2阶段
				if (rest >= 0) {
					ans = Math.min(start, connectGood(dis, lastIndex(start, dis.length), init));
					break;
				}
			}
			start = lastIndex(start, dis.length);
		} while (start != init);
		return ans;
	}

	// 已知start的next方向上有一个良好出发点
	// start如果可以达到这个良好出发点，那么从start出发一定可以转一圈
	public static int connectGood(int[] dis, int start, int init) {
		int ans = dis.length;
		int need = 0;
		while (start != init) {
			if (dis[start] < need) {
				need -= dis[start];
			} else {
				ans = Math.min(ans, start);
				need = 0;
			}
			start = lastIndex(start, dis.length);
		}
		return ans;
	}

	public static int lastIndex(int index, int size) {
		return index == 0 ? (size - 1) : index - 1;
	}

	public static int nextIndex(int index, int size) {
		return index == size - 1 ? 0 : (index + 1);
	}

}
