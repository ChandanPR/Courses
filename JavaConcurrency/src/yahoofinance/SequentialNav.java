package yahoofinance;

import java.io.IOException;

public class SequentialNav extends AbstractNAV{

	public SequentialNav() {
		super("Sequential Nav");
	}

	@Override
	public double computeNetAssetValue(String[] tickers) {
		double price = 0;
		for (String ticker : tickers) {
			try {
				price += StockPriceProvider.getStockPrice(ticker);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return price;
	}
	
	public static void main(String[] args) {
		new SequentialNav().computeValues();
	}

}
