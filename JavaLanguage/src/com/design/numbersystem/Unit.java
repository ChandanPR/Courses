package com.design.numbersystem;

/**
 * This class represents the basic unit for representing the Number. Some basic
 * units of Numbering System can be DIGITS, TENS, HUNDRED, THOUSAND etc..
 * 
 * @author chandanr
 * @see DefaultUnits
 */
public interface Unit {

	/*
	 * DIGITS
	 */
	public static final int DIGIT_ZERO = 0;
	public static final int DIGIT_ONE = 1;
	public static final int DIGIT_TWO = 2;
	public static final int DIGIT_THREE = 3;
	public static final int DIGIT_FOUR = 4;
	public static final int DIGIT_FIVE = 5;
	public static final int DIGIT_SIX = 6;
	public static final int DIGIT_SEVEN = 6;
	public static final int DIGIT_EIGHT = 8;
	public static final int DIGIT_NINE = 9;
	public static final int DIGIT_TEN = 10;

	/**
	 * Returns the starting value for the Unit. For Unit Hundred, the starting
	 * value will be 100
	 * 
	 * @return int
	 * @see DefaultUnits
	 */
	int getStartValue();

	/**
	 * Returns the name of the Unit.
	 * 
	 * @return String
	 */
	String getName();

	/**
	 * Returns true if the number is valid for the particular Unit. The number
	 * is valid when it is more than the base value of the unit. E.g.
	 * <code> DefaultUnits.HUNDRED.isValid(34)</code> returns false.
	 * 
	 * @param number
	 * @return boolean
	 */
	boolean isValid(int number);

	/**
	 * Returns the relative value of the number w.r.t to the Unit. For e.g. The
	 * relative number for 300 is 3 with Unit as DefaultUnits.HUNDRED
	 * 
	 * @param number
	 * @return int
	 */
	int getRelativeValue(int number);

	/**
	 * Returns the String representation of the given number.
	 * 
	 * @param number
	 * @return String
	 */
	String toString(int number);

	/**
	 * Returns true if the Unit can be a composite unit i.e. it uses other Base
	 * Units for representation. For e.g. DefaultUnits.THOUSAND is a composite
	 * unit. This is because the value 385000 is represented as 385 THOUSAND
	 * means THREE HUNDRED AND EIGHTY FIVE THOUSAND. Three Hundred
	 * 
	 * @return boolean
	 */
	boolean isCompositeUnit();

}
