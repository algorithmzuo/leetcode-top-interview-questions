package topinterviewquestions;

public class Problem_0371_SumOfTwoIntegers {

	public static int getSum(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

}
