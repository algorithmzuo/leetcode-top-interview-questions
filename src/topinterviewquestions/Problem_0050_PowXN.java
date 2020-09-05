package topinterviewquestions;

public class Problem_0050_PowXN {

	public static double myPow1(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		if (n == Integer.MIN_VALUE) {
			return (x == 1D || x == -1D) ? 1D : 0;
		}
		int pow = Math.abs(n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	public static double myPow2(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		if (n == Integer.MIN_VALUE) {
			ans *= x;
		}
		return n < 0 ? (1D / ans) : ans;
	}

	public static void main(String[] args) {
		System.out.println("world shut up!");
		int a = Integer.MIN_VALUE;
		int b = -a;
		System.out.println(b);
		double test = 1.0000001D;
		System.out.println(test == 1D);
		System.out.println(Math.pow(test, (double) Integer.MIN_VALUE));
		System.out.println(myPow1(test, Integer.MIN_VALUE));
		System.out.println(myPow2(test, Integer.MIN_VALUE));

	}

}
