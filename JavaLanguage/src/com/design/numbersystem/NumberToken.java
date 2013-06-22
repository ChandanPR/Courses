package com.design.numbersystem;

/**
 * Represents the token for splitting the number into Units.
 * 
 * @author chandanr
 * @see Unit
 * @see DefaultUnits
 * @see NumberTokenizer
 */
public class NumberToken {

	private final int value;
	private final Unit unit;
	private boolean includePrefix;

	/**
	 * Creates the NumberToken with the relative value and Unit.
	 * 
	 * @param value
	 *            : relative value. E.g 3 for 300.
	 * @param unit
	 *            : Unit. E.g. DefaultUnits.HUNDRED for 300
	 */
	public NumberToken(int value, Unit unit) {
		this(value, unit, false);
	}

	/**
	 * Creates the NumberToken with the relative value and Unit.
	 * 
	 * @param value
	 *            : relative value. E.g 3 for 300.
	 * @param unit
	 *            : Unit. E.g. DefaultUnits.HUNDRED for 300
	 * @param includePrefix
	 *            : will be true if a prefix needs to be added to the token.
	 */
	public NumberToken(int value, Unit unit, boolean includePrefix) {
		this.value = value;
		this.unit = unit;
		this.includePrefix = includePrefix;
	}

	/**
	 * Returns the relative value. E.g 3 for 300.
	 * 
	 * @return int
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns the corresponding Unit. E.g. DefaultUnits.HUNDRED for 300
	 * 
	 * @return
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * Returns true if prefix needs to be added for this token while converting
	 * to String.
	 * 
	 * @return boolean
	 */
	public boolean isIncludePrefix() {
		return includePrefix;
	}

	/**
	 * Sets the prefix preference for this token.
	 * 
	 * @param includePrefix
	 */
	public void setIncludePrefix(boolean includePrefix) {
		this.includePrefix = includePrefix;
	}

	@Override
	public String toString() {
		return "NumberToken [value=" + value + ", unit=" + unit
				+ ", includePrefix=" + includePrefix + "]";
	}
	
	

}
