package topinterviewquestions;

public class Problem_0242_ValidAnagram {

	public static boolean isAnagram(String s, String t) {
		char[] str = s.toCharArray();
		char[] tar = t.toCharArray();
		int[] count = new int[256];
		int all = 0;
		for (char cha : str) {
			all++;
			count[cha]++;
		}
		for (char cha : tar) {
			all--;
			if (--count[cha] < 0) {
				return false;
			}
		}
		return all == 0;
	}

}
