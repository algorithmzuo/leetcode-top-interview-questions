package topinterviewquestions;

import java.util.ArrayList;
import java.util.List;

public class Problem_0017_LetterCombinationsOfAPhoneNumber {

	public static char[][] phone = { 
			{ 'a', 'b', 'c' }, 
			{ 'd', 'e', 'f' }, 
			{ 'g', 'h', 'i' }, 
			{ 'j', 'k', 'l' },
			{ 'm', 'n', 'o' }, 
			{ 'p', 'q', 'r', 's' }, 
			{ 't', 'u', 'v' }, 
			{ 'w', 'x', 'y', 'z' }, 
	};

	public static List<String> letterCombinations(String digits) {
		List<String> ans = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return ans;
		}
		char[] str = digits.toCharArray();
		char[] path = new char[str.length];
		process(str, 0, path, ans);
		return ans;
	}

	public static void process(char[] str, int index, char[] path, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(path));
		} else {
			char[] cands = phone[str[index] - '2'];
			for (char cur : cands) {
				path[index] = cur;
				process(str, index + 1, path, ans);
			}
		}
	}

}
