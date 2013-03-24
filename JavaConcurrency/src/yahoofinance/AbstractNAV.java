package yahoofinance;

public abstract class AbstractNAV {
	private static final int COUNT = 100;
	private static final boolean DEBUG = false;
	final String name;

	public AbstractNAV(String name) {
		this.name = name;
	}

	public double computeValues() {
		String[] tickers = new String[COUNT];
		for (int i = 0; i < COUNT; i++) {
			tickers[i] = "IBM";
		}
		long time = System.nanoTime();
		final double value = computeNetAssetValue(tickers);
		if (DEBUG) {
			System.out.println("##########################" + name
					+ "##################");
			System.out.println("Value : " + value);
			System.out.println("Time :" + ((System.nanoTime() - time) / 1.0e9));
			System.out
					.println("###################################################");
		}
		return value;
	}

	public abstract double computeNetAssetValue(String[] tickers);

}
