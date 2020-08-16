package topinterviewquestions;

// Regular Expression Matching问题，进一步做斜率优化，把枚举行为优化掉

public class Problem_0010_RegularExpressionMatching2 {

	public static boolean isValid(char[] str, char[] pattern) {
		for (char cha : str) {
			if (cha == '.' || cha == '*') {
				return false;
			}
		}
		for (int i = 0; i < pattern.length; i++) {
			if (pattern[i] == '*' && (i == 0 || pattern[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isMatch1(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		return isValid(str, pattern) && process(str, pattern, 0, 0);
	}

	public static boolean process(char[] str, char[] pattern, int si, int pi) {
		if (si == str.length) { // si越界了
			if (pi == pattern.length) {
				return true;
			}
			if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
				return process(str, pattern, si, pi + 2);
			}
			return false;
		}
		// si 没越界
		if (pi == pattern.length) {
			return si == str.length;
		}
		// si 没越界 pi 没越界
		if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
			return ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && process(str, pattern, si + 1, pi + 1);
		}
		// si 没越界 pi 没越界 pi+1 *
		if (pattern[pi] != '.' && str[si] != pattern[pi]) {
			return process(str, pattern, si, pi + 2);
		}
		if (process(str, pattern, si, pi + 2)) {
			return true;
		}
		if (si + 1 >= str.length || str[si] != str[si + 1] && (pattern[pi] != '.')) {
			return process(str, pattern, si + 1, pi + 2);
		}
		if (process(str, pattern, si + 1, pi)) {
			return true;
		}
		return false;
	}

	public static boolean isMatch2(String s, String exp) {
		if (s == null || exp == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = exp.toCharArray();
		if (!isValid(str, pattern)) {
			return false;
		}
		boolean[][] dp = initDPMap(str, pattern);
		for (int si = str.length - 1; si >= 0; si--) {
			for (int pi = pattern.length - 2; pi >= 0; pi--) {
				if (pattern[pi + 1] != '*') {
					dp[si][pi] = ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && dp[si + 1][pi + 1];
				} else {
					if (pattern[pi] != '.' && str[si] != pattern[pi]) {
						dp[si][pi] = dp[si][pi + 2];
					} else {
						if (dp[si][pi + 2]) {
							dp[si][pi] = dp[si][pi + 2];
						} else {
							if (si + 1 >= str.length || str[si] != str[si + 1] && (pattern[pi] != '.')) {
								dp[si][pi] = dp[si + 1][pi + 2];
							} else {
								dp[si][pi] = dp[si + 1][pi];
							}
						}
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s, char[] e) {
		int slen = s.length;
		int elen = e.length;
		boolean[][] dp = new boolean[slen + 1][elen + 1];
		dp[slen][elen] = true;
		for (int j = elen - 2; j > -1; j = j - 2) {
			if (e[j] != '*' && e[j + 1] == '*') {
				dp[slen][j] = true;
			} else {
				break;
			}
		}
		if (slen > 0 && elen > 0) {
			if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
				dp[slen - 1][elen - 1] = true;
			}
		}
		return dp;
	}

}
