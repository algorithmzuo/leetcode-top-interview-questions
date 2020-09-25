package topinterviewquestions;

public class Problem_0387_FirstUniqueCharacterInString {

	public int firstUniqChar(String s) {
		char[] str = s.toCharArray();
		int N = str.length;
		int count[] = new int[256];
		for (int i = 0; i < N; i++) {
			count[str[i] - 'a']++;
		}
		for (int i = 0; i < N; i++) {
			if (count[str[i] - 'a'] == 1) {
				return i;
			}
		}
		return -1;
	}

}
