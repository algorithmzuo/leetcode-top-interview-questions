package followup;

import java.util.HashMap;
import java.util.HashSet;

/*
 * 
 * 已知数组中其他数都出现了N次，只有一种数出现了K次
 * 怎么找到出现了K次的数？做到时间复杂度O(N)，额外空间复杂度O(1)
 * 规定：N > 1，K > 0，K < N
 * 
 * */

public class KN {

	public static int get1(int[] arr, int k, int n) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num) + 1);
			}
		}
		for (Integer key : map.keySet()) {
			if (map.get(key) == k) {
				return key;
			}
		}
		return -1;
	}

	public static int get2(int[] arr, int k, int n) {
		int[] count = new int[32];
		for (int num : arr) {
			for (int i = 0; i < 32; i++) {
				if ((num & (1 << i)) != 0) {
					count[i]++;
				}
			}
		}
		for (int i = 0; i < count.length; i++) {
			count[i] = (count[i] % n) / k;
		}
		int ans = 0;
		for (int i = 0; i < count.length; i++) {
			ans |= (count[i] << i);
		}
		return ans;
	}

	public static int[] randomArray(int k, int n, int type) {
		HashSet<Integer> set = new HashSet<>();
		while (set.size() != type - 1) {
			set.add((int) (Math.random() * 10000) - (int) (Math.random() * 10000));
		}
		int knum = 0;
		do {
			knum = (int) (Math.random() * 10000) - (int) (Math.random() * 10000);
		} while (set.contains(knum));
		int[] arr = new int[(type - 1) * n + k];
		int index = 0;
		for (int num : set) {
			for (int i = 0; i < n; i++) {
				arr[index++] = num;
			}
		}
		for (int i = 0; i < k; i++) {
			arr[index++] = knum;
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	public static boolean check(int[] arr, int k, int n, int type) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
			} else {
				map.put(num, map.get(num) + 1);
			}
		}
		int kcount = 0;
		int ncount = 0;
		for (Integer key : map.keySet()) {
			if (map.get(key) == n) {
				ncount++;
			} else if (map.get(key) == k) {
				kcount++;
			} else {
				return false;
			}
		}
		return map.size() == type && kcount == 1 && ncount == type - 1;
	}

	public static void main(String[] args) {
		int knMax = 20;
		int typeMax = 50;
		int testTimes = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int k = 0;
			int n = 0;
			do {
				k = (int) (Math.random() * knMax) + 1;
				n = (int) (Math.random() * knMax) + 2;
			} while (k >= n);
			int type = (int) (Math.random() * typeMax) + 5;
			int[] arr = randomArray(k, n, type);
			if (!check(arr, k, n, type)) {
				System.out.println("random arr error!");
			}
			int ans1 = get1(arr, k, n);
			int ans2 = get2(arr, k, n);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
