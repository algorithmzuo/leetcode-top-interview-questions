package topinterviewquestions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem_0078_Subsets {

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		LinkedList<Integer> path = new LinkedList<>();
		process(nums, 0, path, ans);
		return ans;
	}

	public static void process(int nums[], int index, LinkedList<Integer> path, List<List<Integer>> ans) {
		if (index == nums.length) {
			ans.add(copy(path));
		} else {
			process(nums, index + 1, path, ans);
			path.addLast(nums[index]);
			process(nums, index + 1, path, ans);
			path.removeLast();
		}
	}

	public static ArrayList<Integer> copy(LinkedList<Integer> path) {
		ArrayList<Integer> ans = new ArrayList<>();
		for (Integer num : path) {
			ans.add(num);
		}
		return ans;
	}

}
