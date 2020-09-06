package topinterviewquestions;

public class Problem_0076_MinimumWindowSubstring {

	public static String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] target = t.toCharArray();
		int[] map = new int[256];
		for (char cha : target) {
			map[cha]++;
		}
		int all = target.length;
		int L = 0;
		int R = 0;
		// -1(从来没找到过合法的)
		int minLen = -1;
		int ansl = -1;
		int ansr = -1;
		// [L..R)   [0,0)  R
		while (R != str.length) {
			map[str[R]]--;
			if (map[str[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[str[L]] < 0) {
					map[str[L++]]++;
				}
				if (minLen == -1 || minLen > R - L + 1) {
					minLen = R - L + 1;
					ansl = L;
					ansr = R;
				}
				all++;		
				map[str[L++]]++;
			}
			R++;
		}
		return minLen == -1 ? "" : s.substring(ansl, ansr + 1);
	}

}
