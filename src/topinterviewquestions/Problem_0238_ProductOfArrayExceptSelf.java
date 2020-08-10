package topinterviewquestions;

public class Problem_0238_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
		int zeros = 0;
		int all = 1;
		for (int num : nums) {
			if (num == 0) {
				zeros++;
			} else {
				all *= num;
			}
		}
		if (zeros > 1) {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = 0;
			}
		} else {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = zeros == 0 ? (all / nums[i]) : (nums[i] == 0 ? all : 0);
			}
		}
		return nums;
	}

}
