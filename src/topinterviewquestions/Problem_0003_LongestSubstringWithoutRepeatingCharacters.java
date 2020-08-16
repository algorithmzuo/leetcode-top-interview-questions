package topinterviewquestions;

public class Problem_0003_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		// map (a, ?) (b, ?)
		// a, 17
		// map[97] = 17
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		// 收集答案
		int len = 0;
		int pre = -1; // i-1位置结尾的情况下，往左推，推不动的位置是谁
		int cur = 0;
		for (int i = 0; i != str.length; i++) {
			// i位置结尾的情况下，往左推，推不动的位置是谁
			// pre (i-1信息) -> pre(i 结尾信息)
			pre = Math.max(pre, map[str[i]]);
			cur = i - pre;
			len = Math.max(len, cur);
			map[str[i]] = i;
		}
		return len;
	}

}
