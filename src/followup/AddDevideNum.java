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
public class AddDevideNum {

	public static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static int[] arr = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

	public static int ways(int n) {
		if (map.size() == 0) {
			process(123456789, 8);
		}
		return map.containsKey(n) ? map.get(n) : 0;
	}
	
	public static void process(int num, int index) {
		if (index == -1) {
			for (int add = 8; add >= 2; add--) {
				int p1 = num / arr[add];
				int rest = num % arr[add];
				for (int dev = (add >> 1); dev >= 1; dev--) {
					int p2 = rest / arr[dev];
					int p3 = rest % arr[dev];
					if (p2 % p3 == 0) {
						int ans = p1 + (p2 / p3);
						if (!map.containsKey(ans)) {
							map.put(ans, 1);
						} else {
							map.put(ans, map.get(ans) + 1);
						}
					}
				}
			}
		} else {
			for (int swap = index; swap >= 0; swap--) {
				process(swap(num, index, swap), index - 1);
			}
		}
	}

	public static int swap(int num, int L, int R) {
		int bitL = (num / arr[L]) % 10;
		int bitR = (num / arr[R]) % 10;
		return num + (bitR - bitL) * arr[L] - (bitR - bitL) * arr[R];
	}

	public static void main(String[] args) {
		int N = 100;
		long start;
		long end;
		// 第一次跑要去生成map，需要100多毫秒，
		// 但是只需要在第一次生成，以后都是直接查询的
		start = System.currentTimeMillis();
		System.out.println(N + "用带分数表示的方法数 : " + ways(N));
		end = System.currentTimeMillis();
		System.out.println("运行了(毫秒) : " + (end - start));
		// 第二次跑map已经在上面生成好了，自然很快
		N = 10000;
		start = System.currentTimeMillis();
		System.out.println(N + "用带分数表示的方法数 : " + ways(N));
		end = System.currentTimeMillis();
		System.out.println("运行了(毫秒) : " + (end - start));
	}

}
