package topinterviewquestions;

public class Problem_0188_BestTimeToBuyAndSellStockIV {

	public static int maxProfit1(int K, int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		if (K >= N / 2) {
			return allTrans(arr);
		}
		int[][] dp = new int[N][K + 1];
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				for (int p = 0; p <= i; p++) {
					dp[i][j] = Math.max(dp[p][j - 1] + arr[i] - arr[p], dp[i][j]);
				}
			}
		}
		return dp[N - 1][K];
	}

	public static int maxProfit2(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		int[][] dp = new int[K + 1][N];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			int pre = dp[j][0];
			int best = pre - prices[0];
			for (int i = 1; i < N; i++) {
				pre = dp[j - 1][i];
				dp[j][i] = Math.max(dp[j][i - 1], prices[i] + best);
				best = Math.max(best, pre - prices[i]);
				ans = Math.max(dp[j][i], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

}
