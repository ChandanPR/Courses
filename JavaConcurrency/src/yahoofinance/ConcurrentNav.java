package yahoofinance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ConcurrentNav extends AbstractNAV {

	final int poolSize;
	public ConcurrentNav() {
		super("Concurrent Nav");
		poolSize = (int)(Runtime.getRuntime().availableProcessors()/0.1);
		// TODO Auto-generated constructor stub
	}
	
	public ConcurrentNav(int poolSize) {
		super("Concurrent Nav");
		this.poolSize = poolSize;
	}

	@Override
	public double computeNetAssetValue(String[] tickers) {
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		List<Callable<Double>> tasks = new ArrayList<>();
		for(final String ticker : tickers){
			tasks.add(new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return StockPriceProvider.getStockPrice(ticker);
				}
			});
		}
		double price = 0;
		try {
			List<Future<Double>> results = service.invokeAll(tasks);
			for (Future<Double> result : results) {
				price += result.get();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally{
			service.shutdown();
		}
		return price;
	}
	
	public static void main(String[] args) {
		new ConcurrentNav().computeValues();
	}

}
