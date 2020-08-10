package topinterviewquestions;

public class Problem_0075_SortColors {
	public static void sortColors(int[] nums) {
		int less = -1;
		int more = nums.length;
		int index = 0;
		while (index < more) {
			if (nums[index] == 1) {
				index++;
			} else if (nums[index] == 0) {
				swap(nums, index++, ++less);
			} else {
				swap(nums, index, --more);
			}
		}
	}

	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
