package strings;

public class StringSearch {
	public static void main(String[] args) {
		String prep = "I am preparing for OCJP";
		System.out.println(prep.indexOf('I'));
		System.out.println(prep.indexOf("am"));
		System.out.println(prep.indexOf('I',5));
		System.out.println(prep.indexOf("am",5));
		
		System.out.println(prep.startsWith("I"));
		System.out.println(prep.startsWith("am",2));
		System.out.println(prep.endsWith("JP"));
		
		
		System.out.println(prep.regionMatches(2, "am", 0, "am".length()));
		System.out.println(prep.regionMatches(5, "prep", 0, "prep".length()));
	}

}
