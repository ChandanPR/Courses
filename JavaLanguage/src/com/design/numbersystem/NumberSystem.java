package com.design.numbersystem;

import java.util.ArrayList;

/**
 * This is abstract representation for the Number System. This class can be
 * extended to come up with specific number systems (based on locale, system
 * property etc ..)
 * 
 * <PRE>
 * TODO: Need to add APIs for creating/getting specific number
 * systems. Currently only DefaultNumberSystem is available.
 * </PRE>
 * 
 * @author chandanr
 * @see DefaultNumberSystem
 */
public abstract class NumberSystem {

	protected final NumberTokenizer numberTokenizer;

	protected NumberSystem() {
		numberTokenizer = createNumberTokenizer();
	}

	/**
	 * Returns the Default Number System.
	 * 
	 * @return NumberSystem
	 */
	public static NumberSystem getDefaultNumberSystem() {
		return new DefaultNumberSystem();
	}

	/**
	 * Returns the String representation of the given number.
	 * 
	 * @param number
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public String toString(int number) {
		if(number < 0)
			throw new IllegalArgumentException("The number should be positive");
		
		StringBuilder builder = new StringBuilder();
		ArrayList<NumberToken> tokens = numberTokenizer.getTokens(number);
		int count = tokens.size();
		for (int i = count - 1; i >= 0; i--) {
			processNumberToken(tokens.get(i), builder);
		}
		String string = builder.toString();
		return string.substring(0, string.length() - 1);
	}

	private void processNumberToken(NumberToken numberToken,
			StringBuilder builder) {
		Unit unit = numberToken.getUnit();

		if (numberToken.isIncludePrefix())
			appendUnitPrefix(builder, unit);

		String numberString = "";
		if (unit.isCompositeUnit()) {
			numberString = toString(numberToken.getValue()) + " "
					+ unit.getName();
		} else {
			numberString = unit.toString(numberToken.getValue());
		}
		builder.append(numberString);
		builder.append(" ");
	}

	/**
	 * Appends the Prefix for the unit.
	 * 
	 * @param builder
	 * @param unit
	 */
	protected abstract void appendUnitPrefix(StringBuilder builder, Unit unit);

	/**
	 * Creates the appropriate number tokenizer for the number system.
	 * 
	 * @return NumberTokenizer
	 */
	protected abstract NumberTokenizer createNumberTokenizer();

}
