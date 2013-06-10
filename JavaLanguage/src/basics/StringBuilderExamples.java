package basics;

public class StringBuilderExamples {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder("Examples:");
		builder.append(" ").append("1");
		
		System.out.println(builder);
		
		builder.insert(9,3);
		System.out.println(builder);
	}
}
