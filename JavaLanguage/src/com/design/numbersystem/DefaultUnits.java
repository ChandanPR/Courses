package com.design.numbersystem;

/**
 * Represents the Default Units
 * @author chandanr
 */
enum DefaultUnits implements Unit {

	DIGITS(DIGIT_ONE, DIGIT_ZERO, "", false) {
		private static final String NINETEEN = "NINETEEN";
		private static final String EIGHTEEN = "EIGHTEEN";
		private static final String SEVENTEEN = "SEVENTEEN";
		private static final String SIXTEEN = "SIXTEEN";
		private static final String FIFTEEN = "FIFTEEN";
		private static final String FOURTEEN = "FOURTEEN";
		private static final String THIRTEEN = "THIRTEEN";
		private static final String TWELVE = "TWELVE";
		private static final String ELEVEN = "ELEVEN";
		private static final String NINE = "NINE";
		private static final String EIGHT = "EIGHT";
		private static final String SEVEN = "SEVEN";
		private static final String SIX = "SIX";
		private static final String FIVE = "FIVE";
		private static final String FOUR = "FOUR";
		private static final String THREE = "THREE";
		private static final String TWO = "TWO";
		private static final String ONE = "ONE";
		private final String[] BASE_DIGITS = new String[] { "", ONE, TWO,
				THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN,
				TWELVE, THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN,
				EIGHTEEN, NINETEEN };

		@Override
		public String toString(int number) {
			return BASE_DIGITS[number];
		}

		@Override
		public boolean isValid(int number) {
			return true;
		}

		@Override
		public int getRelativeValue(int number) {
			if (number < 20)
				return number;
			return super.getRelativeValue(number);
		}
	},

	TENS(DIGIT_TWO, DIGIT_ONE, "TENS", false) {
		private static final String NINETY = "NINETY";
		private static final String EIGHTY = "EIGHTY";
		private static final String SEVENTY = "SEVENTY";
		private static final String SIXTY = "SIXTY";
		private static final String FIFTY = "FIFTY";
		private static final String FOURTY = "FOURTY";
		private static final String THIRTY = "THIRTY";
		private final String[] TENS_STRING = { "", TEN, TWENTY, THIRTY, FOURTY,
				FIFTY, SIXTY, SEVENTY, EIGHTY, NINETY };

		@Override
		public String toString(int number) {
			return TENS_STRING[number];
		}
	},

	HUNDRED(DIGIT_THREE, DIGIT_TWO, "HUNDRED", false), THOUSAND(DIGIT_SIX, DIGIT_THREE, "THOUSAND"),
	MILLIONS(DIGIT_NINE, DIGIT_SIX,"MILLION");

	private static final String TEN = "TEN";
	private static final String TWENTY = "TWENTY";

	private final int nextThreshold;
	private final int startValue;
	private final String name;
	private final boolean isCompositeUnit;

	/**
	 * Creates a composite DefaultUnit with the give values
	 * 
	 * @param thresholdExponent
	 *            : the exponent value for the next threshold
	 * @param startExponent
	 *            : the exponent value for the starting value of the unit.
	 * @param unitName
	 *            : Unit Name
	 */
	DefaultUnits(int thresholdExponent, int startExponent, String unitName) {
		this(thresholdExponent, startExponent, unitName, true);
	}

	/**
	 * Creates a DefaultUnit with the given values
	 * 
	 * @param thresholdExponent
	 *            : the exponent value for the next threshold
	 * @param startExponent
	 *            : the exponent value for the starting value of the unit.
	 * @param unitName
	 *            : Unit Name
	 * @param isCompositeUnit
	 *            : true if the unit is a composite unit
	 */
	DefaultUnits(int thresholdExponent, int startExponent, String unitName,
			boolean isCompositeUnit) {
		this.nextThreshold = (int) Math.pow(DIGIT_TEN, thresholdExponent);
		this.startValue = (int) Math.pow(DIGIT_TEN, startExponent);
		this.name = unitName;
		this.isCompositeUnit = isCompositeUnit;
	}

	/**
	 * Returns the name of the Unit.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the starting value for the Unit. For Unit Hundred, the starting
	 * value will be 100
	 * 
	 * @return int
	 * @see DefaultUnits
	 */
	public int getStartValue() {
		return startValue;
	}

	/**
	 * Returns true if the number is valid for the particular Unit. The number
	 * is valid when it is more than the base value of the unit. E.g.
	 * <code> DefaultUnits.HUNDRED.isValid(34)</code> returns false.
	 * 
	 * @param number
	 * @return boolean
	 */
	public boolean isValid(int number) {
		return number / nextThreshold > DIGIT_ZERO;
	}

	/**
	 * Returns the relative value of the number w.r.t to the Unit. For e.g. The
	 * relative number for 300 is 3 with Unit as DefaultUnits.HUNDRED
	 * 
	 * @param number
	 * @return int
	 */
	public int getRelativeValue(int number) {
		int value = isValid(number) ? (value = number % nextThreshold)
				: (number / getStartValue());
		return (value >= getStartValue()) ? (value / getStartValue()) : value;
	}

	/**
	 * Returns the String representation of the given number.
	 * 
	 * @param number
	 * @return String
	 */
	public String toString(int number) {
		return DIGITS.toString(number) + " " + getName();
	}

	/**
	 * Returns true if the Unit can be a composite unit i.e. it uses other Base
	 * Units for representation. For e.g. DefaultUnits.THOUSAND is a composite
	 * unit. This is because the value 385000 is represented as 385 THOUSAND
	 * means THREE HUNDRED AND EIGHTY FIVE THOUSAND. Three Hundred
	 * 
	 * @return boolean
	 */
	public boolean isCompositeUnit() {
		return isCompositeUnit;
	}

}
