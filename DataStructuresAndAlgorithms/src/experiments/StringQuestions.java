package experiments;

import java.util.BitSet;

public class StringQuestions {

	private static boolean checkRepeats(String s) {
		char[] chars = s.toCharArray();
		for (int i = 1; i < chars.length; i++) {
			if (chars[i - 1] == chars[i]) {
				return true;
			}
		}
		return false;
	}

	private static String expand(char[] c) {
		// TODO:
		// CASES: number of times can be more than 1.
		// First implement for 1 and then enhance
		int N = c.length;
		for (int i = N - 1; i >= 0; i++) {
			if (Character.isDigit(c[i])) {
				char c1 = c[i - 1];
			}
		}
		return "";
	}

	private static int countThePalindromes(String str) {
		int count = 0;
		if (isPalindrome(str)) {
			count++;
		}
		int stringLength = str.length();
		for (int i = 0; i < stringLength - 2; i++) {
			for (int j = i + 2; j <= stringLength; j++) {
				if (isPalindrome(str.substring(i, j))) {
					count++;
				}
			}
		}
		return count;
	}

	private static String findLongestPalindrome(String str) {
		if (isPalindrome(str)) {
			return str;
		}
		int maxLength = 1;
		String maxPalindrome = null;
		for (int i = 0; i < str.length() - 2; i++) {
			for (int j = i + 2; j <= str.length(); j++) {
				String substring = str.substring(i, j);
				if (isPalindrome(substring)) {
					int len = j - i;
					if (maxLength <= len) {
						maxLength = len;
						maxPalindrome = substring;
					}
				}
			}
		}
		return maxPalindrome;
	}

	private static boolean isPalindrome(String str) {
		int length = str.length();
		if (length == 1)
			return true;
		int middle = length / 2;
		String first = str.substring(0, middle);
		String second = str.substring(length % 2 == 0 ? middle : middle + 1);
		return first.equals(new StringBuffer(second).reverse().toString());
	}

	private static String replace(String input, String... replacers) {
		// IF THERE IS NO COMBINATIONS - IT WILL BE EASY
		// IF THERE ARE COMBINATIONS YOU SHOULD CHECK BIGGER ONE FIRST
		// E.g. b,bc are to replaced then you need replace bc first else
		// you are ending in error
		// MAY BE IN THIS CASE SORT THE REPLACERS BY DESCENDING ORDER ??!!
		char[] c = new char[1];
		System.out.println(c[0] == '\0');
		return null;
	}

	private static void showProgressiveString(String str) {
		int length = 1;
		int start = 0;
		String previousString = str.substring(0, 1);
		while (start + length < str.length()) {
			String substring = str.substring(start, start + length);
			if (substring.startsWith(previousString)) {
				previousString = substring;
			} else {
				break;
			}
			System.out.println(substring);
			start += length;
			length++;
		}
	}

	private static char findFirstNonRepeatingCharacter(String str) {
		char[] chars = str.toCharArray();
		BitSet set = new BitSet();
		char uniqueChar = '\0';
		for (char c : chars) {
			if(!set.get(c)){
				uniqueChar = c;
				set.set(c);
			}else if(c == uniqueChar){
				uniqueChar = '\0';
			}
		}
		return uniqueChar;
	}

	public static void main(String[] args) {
		testRepeats();
		testPalindromes();
		testProgressiveSubString();
		testFirstNonRepeatingCharacter();
	}

	private static void testFirstNonRepeatingCharacter() {
		assert findFirstNonRepeatingCharacter("ababab") == '\0';
		assert findFirstNonRepeatingCharacter("ababcab") == 'c';
		assert findFirstNonRepeatingCharacter("ababcabdc") == 'd';
		assert findFirstNonRepeatingCharacter("abadbasdsakdkjsadbab") == 'j';
	}

	private static void testProgressiveSubString() {
		showProgressiveString("aababcabcdabcde");
		showProgressiveString("aababcxabcdabcde");
	}

	private static void testPalindromes() {
		assert findLongestPalindrome("ABABAB").equals("BABAB");
		assert countThePalindromes("ABABAB") == 6;
		assert countThePalindromes("ABBAABBA") == 9;
	}

	private static void testRepeats() {
		assert checkRepeats("ABCDE") == false;
		assert checkRepeats("ABCDEE") == true;
		assert checkRepeats("boolean") == true;
		assert checkRepeats("CheEse") == false;
	}

}
