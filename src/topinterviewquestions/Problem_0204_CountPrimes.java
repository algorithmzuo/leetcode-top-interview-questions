package topinterviewquestions;

public class Problem_0204_CountPrimes {

	public static int countPrimes(int n) {
		if (n < 3) {
			return 0;
		}
		boolean[] f = new boolean[n];
		int count = n / 2;
		for (int i = 3; i * i < n; i += 2) {
			if (f[i]) {
				continue;
			}

			// 1
//			int cur = i;
//			int j = i;
//			int p = cur * j;
//			while (p < n) {
//				if (!f[p]) {
//					--count;
//					f[p] = true;
//				}
//				cur += 2;
//				p = j * cur;
//			}
			// 1

			// 2
			for (int j = i * i; j < n; j += 2 * i) {
				if (!f[j]) {
					--count;
					f[j] = true;
				}
			}
			// 2
		}
		return count;
	}

}
