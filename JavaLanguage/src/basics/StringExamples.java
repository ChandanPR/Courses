package basics;

public class StringExamples {
	
	public static void main(String[] args) {
//		String abcdefgh = "ABCDEFGH are alphabets";
		
//		System.out.println(abcdefgh.charAt(3));
//		System.out.println(abcdefgh.indexOf('a'));
//		System.out.println(abcdefgh.indexOf('a',10));
//		System.out.println(abcdefgh.indexOf("bets"));
//		System.out.println(abcdefgh.indexOf("are"));
		
//		String firstName = "Chandan";
//		String lastName = "Ravishankar";
//		String space = " ";
//		String fullName = firstName.concat(space).concat(lastName);
//		System.out.println(fullName);
		
//		System.out.println(fullName.replace('a', 'A'));
//		System.out.println(fullName.replace("an", "AN"));
//		System.out.println(fullName.replace(new String("Chandan "), new String("CHANDAN ")));
//		
//		System.out.println(fullName.replace(new StringBuffer("Chandan "), new StringBuilder("CHANDAN ")));
//		System.out.println(fullName.replace(new StringBuilder("Chandan "), new StringBuffer("CHANDAN ")));
//		
//		
//		System.out.println(fullName.replaceAll("an", "AN"));
//		System.out.println(fullName.replaceFirst("an", "AN"));
		
//		System.out.println(fullName.startsWith("Chandan"));
//		System.out.println(fullName.startsWith("Ravishankar", fullName.indexOf("Ravishankar")));
//		System.out.println(fullName.endsWith("Ravishankar"));
		
		
		String text = " ABCDE ";
		System.out.println(text.length());
		System.out.println(text.substring(2));
		System.out.println(text.substring(1, 5));
		System.out.println("Test"+text.trim()+"Test");
	}

}
