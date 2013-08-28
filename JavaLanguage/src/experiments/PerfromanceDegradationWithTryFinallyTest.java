package experiments;

/**
 * This test is taken from the below BUG
 * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4493074
 * 
 * Very Old Bug on 1.3
 * 
 * -XX:+PrintOptoBailouts,-XX:+TraceOptoParse, -XX:+TraceDeoptimization: 
 * The above flags are not available in the VM Options now.
 * Not Sure what this flag was about.
 * @author chandanpr
 * 
 */
public class PerfromanceDegradationWithTryFinallyTest {
	public static void main(String argv[]) {
		int i, j;
		i = 10;
		j = 20;
		foo(i, j);
	}

	public static void foo(int i, int j) {
		for (i = 0; i < 20000; i++)
			nextmethod(i);
	}

	public static void nextmethod(int i) {
		int retval;
		Tag tag = null;

		// NEEDS 2 levels of try's to FAIL
		try {
			// stuff
			tag = null;
			try {
				tag = new Tag();
				// NEEDS the "if ..... throw" lines to FAIL
				retval = tag.doStartTag();
				if (retval == 2) {
					throw new Exception("An exception occurred!");
				}
				retval = tag.doEndTag();
				if (retval == 5)
					return;
			} catch (Exception e1) {
				throw new Exception("Caught exception. Throw again.");
			} finally {
				// NEED THIS TO FAIL
//				System.out.println("finally...\n");
			}
		} catch (Exception e) {
			return;
		}
	}

}

class Tag {
	public int doStartTag() {
		return 5;
	}

	public int doEndTag() {
		return 5;
	}
}
