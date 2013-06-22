package experiments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class StringQuestions {
	
	private static boolean checkRepeats(String s){
		char[] chars = s.toCharArray();
		for(int i=1; i<chars.length; i++){
			if(chars[i-1] == chars[i]){
				return true;
			}
		}
		return false;
	}
	
	private static String expand(char[] c){
		//TODO:
		//CASES: number of times can be more than 1.
		//First implement for 1 and then enhance
		int N = c.length;
		for(int i=N-1; i >=0; i++){
			if(Character.isDigit(c[i])){
				char c1 = c[i-1];
			}
		}
		return "";
	}
	
	private static String replace(String input, String... replacers){
		//IF THERE IS NO COMBINATIONS - IT WILL BE EASY
		//IF THERE ARE COMBINATIONS YOU SHOULD CHECK BIGGER ONE FIRST
		//E.g. b,bc are to replaced then you need replace bc first else
		//you are ending in error
		//MAY BE IN THIS CASE SORT THE REPLACERS BY DESCENDING ORDER ??!!
		return null;
	}
	
	
	public static void main(String[] args) {
		testRepeats();
		char[] c = new char[1];
		System.out.println(c[0] == '\0');
	}


	private static void testRepeats() {
		assert checkRepeats("ABCDE") == false;
		assert checkRepeats("ABCDEE") == true;
		assert checkRepeats("boolean") == true;
		assert checkRepeats("CheEse") == false;
	}

}
