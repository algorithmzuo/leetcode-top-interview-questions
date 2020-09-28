package topinterviewquestions;

public class Problem_0200_NumberOfIslands {

	public static int numIslands(char[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int N = m.length;
		int M = m[0].length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == '1') {
					res++;
					infect(m, i, j, N, M);
				}
			}
		}
		return res;
	}

	// 目前来到m[i][j], 经历上下左右的感染过程
	public static void infect(char[][] m, int i, int j, int N, int M) {
		if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != '1') {
			return;
		}
		m[i][j] = '2';
		infect(m, i + 1, j, N, M);
		infect(m, i - 1, j, N, M);
		infect(m, i, j + 1, N, M);
		infect(m, i, j - 1, N, M);
	}

}
