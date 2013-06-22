package com.design.numbersystem;

import java.util.ArrayList;

/**
 * This class represents the tokenizer for the number system. For any given
 * number it creates the appropriate token for each Unit and returns the result
 * tokens list.
 * 
 * @author chandanr
 */
public interface NumberTokenizer {

	/**
	 * For any given number it creates the appropriate token for each Unit and
	 * returns the result tokens list.
	 * 
	 * @param number
	 * @return: ArrayList<NumberToken>
	 */
	ArrayList<NumberToken> getTokens(int number);

}
