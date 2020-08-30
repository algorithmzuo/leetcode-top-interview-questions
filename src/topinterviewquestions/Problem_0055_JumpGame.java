package topinterviewquestions;

public class Problem_0055_JumpGame {

	public static boolean canJump(int[] nums) {
		if (nums == null || nums.length < 2) {
			return true;
		}
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
//			if (max >= nums.length - 1) {
//				return true;
//			}
			if (i > max) {
				return false;
			}
			max = Math.max(max, i + nums[i]);
		}
		return true;
	}

}
