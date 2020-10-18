package followup;

public class Code03_Array {

	public static int findError(String[] contents) {
		if (contents == null || contents.length == 0) {
			return 0;
		}
		String arrName = contents[0].substring(0, contents[0].indexOf("["));
		int arrSize = Integer.valueOf(contents[0].substring(contents[0].indexOf("[") + 1, contents[0].indexOf("]")));
		int[] arr = new int[arrSize];
		for (int i = 1; i < contents.length; i++) {
			String[] parts = contents[i].replace(" ", "").split("=");
			String left = parts[0].replace(arrName, "");
			left = left.substring(1, left.length() - 1);
			String right = parts[1].replace(arrName, "");
			Integer leftIndex = value(left, arr);
			Integer rightValue = value(right, arr);
			if (leftIndex == null || rightValue == null) {
				return i;
			}
			if (leftIndex < 0 || leftIndex >= arrSize) {
				return i;
			}
			arr[leftIndex] = rightValue;
		}
		return 0;
	}

	// str ->  "  [[[7]]]  "  返回什么
	public static Integer value(String str, int[] arr) {
		int value = Integer.valueOf(str.replace("[", "").replace("]", ""));
		int levels = str.lastIndexOf("[") + 1;
		for (int i = 0; i < levels; i++) {
			if (value < 0 || value >= arr.length) {
				return null;
			}
			value = arr[value];
		}
		return value;
	}

	public static void main(String[] args) {
		String[] contents = { 
				"arr[7]", 
				"arr[0]=6", 
				"arr[1]=3", 
				"arr[2]=1", 
				"arr[3]=2", 
				"arr[4]=4", 
				"arr[5]=0",
				"arr[6]=5", 
				"arr[arr[1]]=3", 
				"arr[arr[arr[arr[5]]]]=arr[arr[arr[3]]]",
				"arr[arr[4]]=arr[arr[arr[0]]]",
				"arr[arr[1]] = 7", 
				"arr[0] = arr[arr[arr[1]]]" };
		System.out.println(findError(contents));

	}

}
