package topinterviewquestions;

public class Problem_0010_RegularExpressionMatching {

	public static boolean isMatch1(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		return isValid(str, pattern) && process(str, pattern, 0, 0);
	}

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

	// str[si.....] 能否被 pattern[pi...] 变出来
	// 潜台词：pi位置，pattern[pi] != '*'
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
		while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
			if (process(str, pattern, si + 1, pi + 2)) {
				return true;
			}
			si++;
		}
		return false;
	}

	public static boolean isMatch2(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		char[] str = s.toCharArray();
		char[] pattern = p.toCharArray();
		int[][] dp = new int[str.length + 1][pattern.length + 1];
		for (int si = 0; si <= str.length; si++) {
			for (int pi = 0; pi <= pattern.length; pi++) {
				dp[si][pi] = -1;
			}
		}
		// dp[si][pi] == -1
		// dp[si][pi] == 0 si pi false
		// dp[si][pi] == 1 si pi true
		return isValid(str, pattern) && process2(str, pattern, 0, 0, dp);
	}

	// str[si.....] 能否被 pattern[pi...] 变出来
	// 潜台词：pi位置，pattern[pi] != '*'
	public static boolean process2(char[] str, char[] pattern, int si, int pi, int[][] dp) {
		if (dp[si][pi] != -1) {
			return dp[si][pi] == 1;
		}
		// si pi 这个参数组合第一次算

		if (si == str.length) { // si越界了
			if (pi == pattern.length) {
				dp[si][pi] = 1;
				return true;
			}
			// (pi pi+1) pi+2 ....
			if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
				boolean ans = process2(str, pattern, si, pi + 2, dp);
				dp[si][pi] = ans ? 1 : 0;
				return ans;
			}
			dp[si][pi] = 0;
			return false;
		}
		// si 没越界
		if (pi == pattern.length) {
			boolean ans = si == str.length;
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		// si 没越界 pi 没越界
		if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
			boolean ans = ((str[si] == pattern[pi]) || (pattern[pi] == '.'))
					&& process2(str, pattern, si + 1, pi + 1, dp);
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		// si 没越界 pi 没越界 pi+1 *
		if (pattern[pi] != '.' && str[si] != pattern[pi]) {
			boolean ans = process2(str, pattern, si, pi + 2, dp);
			dp[si][pi] = ans ? 1 : 0;
			return ans;
		}
		if (process2(str, pattern, si, pi + 2, dp)) {
			dp[si][pi] = 1;
			return true;
		}
		while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
			if (process2(str, pattern, si + 1, pi + 2, dp)) {
				dp[si][pi] = 1;
				return true;
			}
			si++;
		}
		dp[si][pi] = 0;
		return false;
	}

	public static boolean isMatch3(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		if (!isValid(s, e)) {
			return false;
		}
		boolean[][] dp = initDPMap(s, e);
		for (int i = s.length - 1; i > -1; i--) {
			for (int j = e.length - 2; j > -1; j--) {
				if (e[j + 1] != '*') {
					dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
				} else {
					int si = i;
					while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
						if (dp[si][j + 2]) {
							dp[i][j] = true;
							break;
						}
						si++;
					}
					if (dp[i][j] != true) {
						dp[i][j] = dp[si][j + 2];
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
