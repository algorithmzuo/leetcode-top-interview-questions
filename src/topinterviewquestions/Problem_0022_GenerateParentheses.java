package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

public class Problem_0022_GenerateParentheses {

	public static List<String> generateParenthesis(int n) {
		char[] str = new char[n << 1];
		List<String> ans = new ArrayList<>();
		process(str, 0, 0, n, ans);
		return ans;
	}

	public static void process(char[] str, int index, int leftNum, int leftRest, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			if (leftRest > 0) {
				str[index] = '(';
				process(str, index + 1, leftNum + 1, leftRest - 1, ans);
			}
			if (leftNum > 0) {
				str[index] = ')';
				process(str, index + 1, leftNum - 1, leftRest, ans);
			}
		}
	}

}
