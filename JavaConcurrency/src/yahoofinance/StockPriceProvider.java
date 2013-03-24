package yahoofinance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class StockPriceProvider {
	
	public static final double getStockPrice(String ticker) throws IOException{
		URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=IBM");
		BufferedReader urlReader = new BufferedReader(new InputStreamReader(url.openStream()));
		//Skip the first line as it is header
		urlReader.readLine();
		
		String latestData = urlReader.readLine();
		String[] datas = latestData.split(",");
		return Double.valueOf(datas[datas.length - 1]);
	}

}
