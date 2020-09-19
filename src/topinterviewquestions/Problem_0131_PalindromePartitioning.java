package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0131_PalindromePartitioning {

	public static List<List<String>> partition(String s) {
		// dp[L][R] -> 是不是回文
		boolean[][] dp = getdp(s.toCharArray());
		LinkedList<String> path = new LinkedList<>();
		List<List<String>> ans = new ArrayList<>();
		process(s, 0, path, dp, ans);
		return ans;
	}

	public static boolean[][] getdp(char[] str) {
		int N = str.length;
		boolean[][] dp = new boolean[N][N];
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = true;
			dp[i][i + 1] = str[i] == str[i + 1];
		}
		dp[N - 1][N - 1] = true;
		for (int j = 2; j < N; j++) {
			int row = 0;
			int col = j;
			while (row < N && col < N) {
				dp[row][col] = str[row] == str[col] && dp[row + 1][col - 1];
				row++;
				col++;
			}
		}
		return dp;
	}

	// s 字符串
	// s[0...index-1] 已经做过的决定，放入了path中
	// 在index开始做属于这个位置的决定，
	// index == s.len  path之前做的决定（一种分割方法），放进总答案ans里
	public static void process(String s, int index, LinkedList<String> path, 
			boolean[][] dp, List<List<String>> ans) {
		if (index == s.length()) {
			ans.add(copy(path));
		} else {
			for (int end = index; end < s.length(); end++) {
				// index..index   
				// index..index+1
				// index..index+2
				// index..end
				if (dp[index][end]) {
					path.addLast(s.substring(index, end + 1));
					process(s, end + 1, path, dp, ans);
					path.pollLast();
				}
			}
		}
	}

	public static List<String> copy(List<String> path) {
		List<String> ans = new ArrayList<>();
		for (String p : path) {
			ans.add(p);
		}
		return ans;
	}

}
