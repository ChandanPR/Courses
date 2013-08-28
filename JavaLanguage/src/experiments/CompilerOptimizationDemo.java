package experiments;

/**
 * 
 * This piece of code is taken by the below blog from Martin Thomson
 * http://mechanical-sympathy.blogspot.co.uk/2012/04/invoke-interface-optimisations.html
 * log1.txt - -XX:+PrintCompilation -XX:+TieredCompilation
 * log2.txt - -XX:+PrintCompilation
 * log3.txt - -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation
 * 
 * 
 * Use the below command to check the available VM Options:
 * java -XX:+AggressiveOpts -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:+PrintFlagsFinal -version
 * 
 * http://stas-blogspot.blogspot.in/2011/07/most-complete-list-of-xx-options-for.html
 * 
 * 
 * Explanation on made non entrant and zombie:
 * http://stackoverflow.com/questions/2930838/java-printcompilation-output-whats-the-meaning-of-made-not-entrant-and-made
 * 
 * Explanation on -XX:+PrintCompilation output details:
 * http://blog.joda.org/2011/08/printcompilation-jvm-flag.html
 * 
 * HotSpot Internals Home
 * https://wikis.oracle.com/display/HotSpotInternals/Home
 * 
 * http://stackoverflow.com/questions/9336704/jvm-option-to-optimize-loop-statements#answer-9337542
 * 
 * 
 * -XX:PrintCompilation - explanation link
 * https://gist.github.com/rednaxelafx/1165804#file_notes.md
 * 
 * 
 * To Turn off BackGround Compilation use:
 * -Xbatch or -XX:-BackgroundCompilation
 * 
 * To run the VM in compiled mode use:
 * -Xcomp or -XX:+CompileTheWorld or -Xconcurrentio
 * 
 * @author chandanpr
 *
 */
public final class CompilerOptimizationDemo
{
    private static final int ITERATIONS = 50 * 1000 * 1000;
    private static final StringBuilder builder = new StringBuilder();
 
    public static void main(final String[] args)
        throws Exception
    {
        final Operation[] operations = new Operation[4];
        int index = 0;
        operations[index++] = new StepIncOperation();
        operations[index++] = new StepDecOperation();
        operations[index++] = new IncOperation();
        operations[index++] = new DecOperation();
 
        int value = 777;
        for (int i = 0; i < 3; i++)
        {
            builder.append("*** Run each method in turn: loop " + i+"\n");
 
            for (final Operation operation : operations)
            {
            	builder.append(operation.getClass().getName()+"\n"); 
                value = runTests(operation, value);
            }
        } 
 
        builder.append("value = " + value);
        Thread.sleep(1000);
        System.out.println(builder.toString());
    } 
 
    private static int runTests(final Operation operation, int value)
    {
        for (int i = 0; i < 10; i++)
        {
            final long start = System.nanoTime();
 
            value += opRun(operation, value);
 
            final long duration = System.nanoTime() - start;
            final long opsPerSec = 
                (ITERATIONS * 1000L * 1000L * 1000L) / duration;
            builder.append(String.format("    %,d ops/sec\n", opsPerSec)+"\n");
//            System.out.printf("    %,d ops/sec\n", opsPerSec);
        }
 
        return value;
    } 
 
    private static int opRun(final Operation operation, int value)
    {
        for (int i = 0; i < ITERATIONS; i++)
        {
            value += operation.map(value);
        } 
 
        return value;
    } 
}


interface Operation
{
    int map(int value);
}
 
class IncOperation implements Operation
{
    public int map(final int value)
    {
        return value + 1;
    }
}
 
class DecOperation implements Operation
{
    public int map(final int value)
    {
        return value - 1;
    }
}
 
class StepIncOperation implements Operation
{
    public int map(final int value)
    {
        return value + 7;
    }
}
 
class StepDecOperation implements Operation
{
    public int map(final int value)
    {
        return value - 3;
    }
}
 
