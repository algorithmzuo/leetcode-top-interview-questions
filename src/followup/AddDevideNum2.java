package followup;

import java.util.HashMap;

/*
 * 100 = 3 + 69258 / 714
 * 100 = 82 + 3546 / 197
 * 
 * 等号右边的部分，可以写成 p1 + p2 / p3的形式
 * 要求p1和p2和p3，所使用的数字，必须把1~9使用完全，并且不重复
 * 满足的话，我们就说，形如p1 + p2 / p3，一个有效的"带分数"形式
 * 要求，p2 / p3 必须整除
 * 
 * 输入N，返回N有多少种带分数形式
 * 100，有11种带分数形式
 * 
 * 输入的N，N  < 10的8次方
 * 
 * 
 * */
public class AddDevideNum2 {

	// 1-9 所有带分数，形成的结果，都算一遍，map里去
	// key 100 value 11
	public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public final static int[] arr = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

	public static int ways(int n) {
		if (map.size() == 0) {
			process(123456789, 8);
		}
		return map.containsKey(n) ? map.get(n) : 0;
	}

	// process, 当的数形成样子，num 123456789
	// index 该哪个位置的数字去指定了
	public static void process(int num, int index) {
		if (index == -1) {
			// num 固定了 8 ~ 0
			// num + / 8 ~ 0
			for (int addSplit = 8; addSplit >= 2; addSplit--) {
				// p1
				int p1 = num / arr[addSplit];
				int rest = num % arr[addSplit];
				for (int devSplit = (addSplit >> 1); devSplit >= 1; devSplit--) {
					int p2 = rest / arr[devSplit];
					int p3 = rest % arr[devSplit];
					if (p2 % p3 == 0) {
						int ans = p1 + p2 / p3;
						if (!map.containsKey(ans)) {
							map.put(ans, 1);
						} else {
							map.put(ans, map.get(ans) + 1);
						}
					}
				}
			}
		} else {
			// 需要去指定index位置的数
			for (int swap = index; swap >= 0; swap--) {
				int next = swap(num, index, swap);
				process(next, index - 1);
			}
		}
	}

	// 123456789 L == 8 R == 2

	// 723456189
	public static int swap(int num, int L, int R) {
		// num L位的数字是什么？
		int bitL = (num / arr[L]) % 10;
		int bitR = (num / arr[R]) % 10;
		return num - (bitL - bitR) * arr[L] + (bitL - bitR) * arr[R];
	}

	public static void main(String[] args) {
		// 876543210
//		int test = 672381945;
//		int ans = swap(test, 8, 0);
//		System.out.println(ans);
//		process(123456789, 8);

		long start;
		long end;
		start = System.currentTimeMillis();
		System.out.println(ways(100));
		end = System.currentTimeMillis();
		System.out.println(end - start);

//		int N = 100;
//		long start;
//		long end;
//		// 第一次跑要去生成map，需要100多毫秒，
//		// 但是只需要在第一次生成，以后都是直接查询的
//		start = System.currentTimeMillis();
//		System.out.println(N + "用带分数表示的方法数 : " + ways(N));
//		end = System.currentTimeMillis();
//		System.out.println("运行了(毫秒) : " + (end - start));
//		// 第二次跑map已经在上面生成好了，自然很快
//		N = 10000;
//		start = System.currentTimeMillis();
//		System.out.println(N + "用带分数表示的方法数 : " + ways(N));
//		end = System.currentTimeMillis();
//		System.out.println("运行了(毫秒) : " + (end - start));
	}

}
