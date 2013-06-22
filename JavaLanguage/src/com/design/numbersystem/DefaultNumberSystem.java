package com.design.numbersystem;

import java.util.ArrayList;

/**
 * Represents the Default NumberSystem
 * 
 * @author chandanr
 */
class DefaultNumberSystem extends NumberSystem {

	class DefaultNumberTokenizer implements NumberTokenizer {

		/**
		 * For any given number it creates the appropriate token for each Unit
		 * and returns the result tokens list.
		 * 
		 * @param number
		 * @return: ArrayList<NumberToken>
		 */
		public ArrayList<NumberToken> getTokens(int number) {
			ArrayList<NumberToken> tokens = new ArrayList<NumberToken>();
			int currentNumber = number;
			for (DefaultUnits unit : DefaultUnits.values()) {
				checkAndSetPrefixValue(tokens, unit);
				int representingValue = unit.getRelativeValue(currentNumber);
				if (representingValue != Unit.DIGIT_ZERO)
					tokens.add(new NumberToken(representingValue, unit));
				if (!unit.isValid(currentNumber)) {
					break;
				}
				currentNumber -= representingValue * unit.getStartValue();
				//Doing the merge here since a if condition is better than array copy.
				checkAndMergeTokens(tokens);
			}
			return tokens;
		}
		
		/**
		 * In some cases when the number is greater than hundred and tens value is less than twenty merge is needed.
		 * Some examples are like 111, 213, 315 etc...
		 * @param tokens
		 */
		private void checkAndMergeTokens(ArrayList<NumberToken> tokens){
			
			if(tokens.size() != 2)
				return;
			
			NumberToken tokenOne = tokens.get(0);
			NumberToken tokenTwo = tokens.get(1);
			int value1 = tokenOne.getValue()*tokenOne.getUnit().getStartValue();
			int value2 = tokenTwo.getValue()*tokenTwo.getUnit().getStartValue();
			int total = value1+value2;
			if(total < 20){
				boolean includePrefix = tokenOne.isIncludePrefix() || tokenTwo.isIncludePrefix();
				tokens.clear();
				tokens.add(new NumberToken(total, DefaultUnits.DIGITS,includePrefix));
			}
			
		}

		/**
		 * Checks if the prefix is needed for the unit and sets the value
		 * accordingly to the token.
		 * 
		 * @param tokens
		 * @param unit
		 */
		private void checkAndSetPrefixValue(ArrayList<NumberToken> tokens,
				Unit unit) {
			int size = tokens.size();
			int ten = DefaultUnits.TENS.getStartValue();
			int hundred = DefaultUnits.HUNDRED.getStartValue();
			if (size != Unit.DIGIT_ZERO && (unit.getStartValue() > ten)
					&& tokens.get(size - 1).getUnit().getStartValue() < hundred) {
				tokens.get(size - 1).setIncludePrefix(true);
			}
		}

	}

	/**
	 * Returns the String representation of the given number.
	 * 
	 * @param number
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public String toString(int number) {
		if (number == Unit.DIGIT_ZERO)
			return "ZERO";
		return super.toString(number);
	}

	/**
	 * Appends the Prefix for the unit.
	 * 
	 * @param builder
	 * @param unit
	 */
	protected void appendUnitPrefix(StringBuilder builder, Unit unit) {
		builder.append("AND ");
	}

	/**
	 * Creates the appropriate number tokenizer for the number system.
	 * 
	 * @return NumberTokenizer
	 */
	protected NumberTokenizer createNumberTokenizer() {
		return new DefaultNumberTokenizer();
	}

}
