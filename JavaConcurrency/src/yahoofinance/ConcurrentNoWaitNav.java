package yahoofinance;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentNoWaitNav extends AbstractNAV{

	public ConcurrentNoWaitNav() {
		super("Concurrent NoWait Nav");
	}

	@Override
	public double computeNetAssetValue(String[] tickers) {
		double price = 0;
		int poolSize = (int)(Runtime.getRuntime().availableProcessors()/0.1);
		ExecutorService service = Executors.newFixedThreadPool(poolSize);
		CompletionService<Double> completionService = new ExecutorCompletionService<>(service);
		for(final String ticker : tickers){
			completionService.submit(new Callable<Double>() {
				@Override
				public Double call() throws Exception {
					return StockPriceProvider.getStockPrice(ticker);
				}
			});
		}
		try {
			for(String ticker : tickers){
				Future<Double> result = completionService.take();
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

}
