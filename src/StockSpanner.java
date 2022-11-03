/**
 *901. 股票价格跨度
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 *
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 *
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 */
public class StockSpanner {
    int[] prices;
    int[] spanners;
    int idx;
    public StockSpanner() {
        prices = new int[10000];
        spanners = new int[10000];
        idx = 0;
    }

    public int next(int price) {
        prices[idx] = price;
        spanners[idx] = 1;
        int cur = idx;
        while (cur >= 0 && prices[cur] <= price) {
            cur -= spanners[cur];
        }
        spanners[idx] = idx - cur                                                                                                                                                                                                           ;
        return spanners[idx++];
    }
}
