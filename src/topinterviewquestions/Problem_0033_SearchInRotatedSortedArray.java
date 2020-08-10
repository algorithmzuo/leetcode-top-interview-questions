package topinterviewquestions;

public class Problem_0033_SearchInRotatedSortedArray {

	public static int search(int[] arr, int num) {
		int low = 0;
		int high = arr.length - 1;
		int mid = 0;
		while (low <= high) {
			mid = (low + high) / 2;
			if (arr[mid] == num) {
				return mid;
			}
			if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
				while (low != mid && arr[low] == arr[mid]) {
					low++;
				}
				if (low == mid) {
					low = mid + 1;
					continue;
				}
			}
			if (arr[low] != arr[mid]) {
				if (arr[mid] > arr[low]) {
					if (num >= arr[low] && num < arr[mid]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				} else {
					if (num > arr[mid] && num <= arr[high]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}
			} else {
				if (arr[mid] < arr[high]) {
					if (num > arr[mid] && num <= arr[high]) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				} else {
					if (num >= arr[low] && num < arr[mid]) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}
			}
		}
		return -1;
	}

}
