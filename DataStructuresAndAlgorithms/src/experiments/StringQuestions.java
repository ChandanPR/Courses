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
		int N = c.length;
		int start = 0;
		for (int i = 1; i < N; i++) {
			if (Character.isDigit(c[i])) {
				int count = Character.digit(c[i], 10);
				fill(c, start, i, count, c[i - 1]);
				start += count;
			}
		}
		return new String(c);
	}

	private static void fill(char[] c, int start, int current, int count,
			char c1) {
		if (count == 1)
			return;
		System.out.println("Fill 1"+new String(c)+" : "+ start+" : "+current+" : "+count+" : "+c1);
		if (start + count > c.length)
			throw new RuntimeException();
		int shifts = count - current - start + 1;
		if (shifts > 0) {
			shift(current, c, shifts);
		}
		System.out.println("Fill 2"+new String(c));
		for (int i = 0; i < count; i++) {
			c[start + i] = c1;
		}
		System.out.println("Fill 3"+new String(c));
	}

	private static void shift(int current, char[] c, int shifts) {
		int N = c.length;
		for (int i = 0; i < shifts; i++) {
			int last = N - 1;
			while (last > current) {
				c[last] = c[last - 1];
				last--;
			}
		}
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
		if (length == 2) {
			return str.charAt(0) == str.charAt(1);
		}
		int middle = length / 2;
		String first = str.substring(0, middle);
		String second = str.substring(length % 2 == 0 ? middle : middle + 1);
		return first.equals(reverse(second));
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

	private static char findLastNonRepeatingCharacter(String str) {
		char[] chars = str.toCharArray();
		BitSet set = new BitSet();
		char uniqueChar = '\0';
		for (char c : chars) {
			if (!set.get(c)) {
				uniqueChar = c;
				set.set(c);
			} else if (c == uniqueChar) {
				uniqueChar = '\0';
			}
		}
		return uniqueChar;
	}

	private static String reverse(String str) {
		char[] c = str.toCharArray();
		int N = c.length;
		for (int i = 0; i < N / 2; i++) {
			char temp = c[N - i - 1];
			c[N - i - 1] = c[i];
			c[i] = temp;
		}
		return new String(c);
	}

	public static void main(String[] args) {
		// testRepeats();
		// testPalindromes();
		// testProgressiveSubString();
		// testFirstNonRepeatingCharacter();
		// testReverse();

		testExpand();
	}

	private static void testExpand() {
		assert expand("A1C2B4 ".toCharArray()).equals("ACCBBBB");
		assert expand("A1C4B2 ".toCharArray()).equals("ACCCCBB");
		//DOESN'T WORK FOR CASE WHERE FIRST ONE IS HAVING MORE COUNT???!!!!!
//		assert expand("A4C1B2 ".toCharArray()).equals("AAAACBB");
	}

	private static void testReverse() {
		assert reverse("ABC").equals("CBA");
		assert reverse("ABCD").equals("DCBA");
	}

	private static void testFirstNonRepeatingCharacter() {
		assert findLastNonRepeatingCharacter("ababab") == '\0';
		assert findLastNonRepeatingCharacter("abdababf") == 'f';
		assert findLastNonRepeatingCharacter("ababcab") == 'c';
		assert findLastNonRepeatingCharacter("ababcabdc") == 'd';
		assert findLastNonRepeatingCharacter("abadbasdsakdkjsadbab") == 'j';
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
