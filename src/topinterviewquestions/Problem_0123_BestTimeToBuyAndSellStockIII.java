package topinterviewquestions;

public class Problem_0123_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;// 0 : [0] - [0]
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			min = Math.min(min, prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
