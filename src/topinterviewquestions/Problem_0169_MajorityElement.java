package topinterviewquestions;

public class Problem_0169_MajorityElement {

	public static int majorityElement(int[] nums) {
		int cand = 0;
		int HP = 0;
		for (int i = 0; i < nums.length; i++) {
			if (HP == 0) {
				cand = nums[i];
				HP = 1;
			} else if (nums[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		return cand;
	}

}
