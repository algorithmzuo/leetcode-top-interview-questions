package topinterviewquestions;

public class Problem_0044_WildcardMatching {

	public static boolean isMatch1(String s, String p) {
		return process(s.toCharArray(), p.toCharArray(), 0, 0);
	}

	public static boolean process(char[] str, char[] pattern, int si, int pi) {
		if (si == str.length) {
			if (pi == pattern.length) {
				return true;
			}
			for (; pi < pattern.length; pi++) {
				if (pattern[pi] != '*') {
					return false;
				}
			}
			return true;
		}
		if (pi == pattern.length) {
			return si == str.length;
		}
		if (pattern[pi] != '*') {
			return (str[si] == pattern[pi] || pattern[pi] == '?') && process(str, pattern, si + 1, pi + 1);
		} else {
			for (int i = si; i <= str.length; i++) {
				if (process(str, pattern, i, pi + 1)) {
					return true;
				}
			}
			return false;
		}
	}

	public static boolean isMatch2(String s, String p) {
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		int N = str.length;
		int M = pattern.length;
		boolean[][] dp = new boolean[N + 1][M + 1];
		dp[N][M] = true;
		for (int pi = M - 1; pi >= 0; pi--) {
			if (pattern[pi] != '*') {
				break;
			}
			dp[N][pi] = true;
		}
		for (int pi = M - 1; pi >= 0; pi--) {
			for (int si = 0; si < N; si++) {
				if (pattern[pi] != '*') {
					dp[si][pi] = (str[si] == pattern[pi] || pattern[pi] == '?') && dp[si + 1][pi + 1];
				} else {
					for (int i = si; i <= str.length; i++) {
						if (dp[i][pi + 1]) {
							dp[si][pi] = true;
							break;
						}
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean isMatch3(String s, String p) {
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		int N = str.length;
		int M = pattern.length;
		boolean[][] dp = new boolean[N + 1][M + 1];
		dp[N][M] = true;
		for (int pi = M - 1; pi >= 0; pi--) {
			if (pattern[pi] != '*') {
				break;
			}
			dp[N][pi] = true;
		}
		for (int pi = M - 1; pi >= 0; pi--) {
			for (int si = N - 1; si >= 0; si--) {
				if (pattern[pi] != '*') {
					dp[si][pi] = (str[si] == pattern[pi] || pattern[pi] == '?') && dp[si + 1][pi + 1];
				} else {
					dp[si][pi] = dp[si][pi + 1] || dp[si + 1][pi];
				}
			}
		}
		return dp[0][0];
	}

}
